package com.example.books;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.books.models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Book> books = new ArrayList<>();
    private Map<String, List<Book>> categoryBooks = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAdapter = new RecyclerViewAdapter(categoryBooks);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        getBooks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_search_book:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Buscar Libro");
                View viewInflated = LayoutInflater.from(this).inflate(R.layout.search_dialog, null);
                final EditText input = (EditText) viewInflated.findViewById(R.id.search_dialog_input);
                builder.setView(viewInflated);
                builder.setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        final String search = input.getText().toString().toLowerCase();
                        Map<String, List<Book>> filteredBooks = new HashMap<>();
                        for(Book book : books) {
                            if (book.getTitle().contains(search) ||
                                    book.getAutor().getFirst_name().toLowerCase().contains(search) ||
                                    book.getAutor().getLast_name().toLowerCase().contains(search) ||
                                    book.getDescription().toLowerCase().contains(search) ||
                                    book.getId().toLowerCase().contains(search) ||
                                    book.getCategory().toLowerCase().contains(search) ||
                                    book.getIsbn().toLowerCase().contains(search) ||
                                    book.getPublisher().toLowerCase().contains(search) ||
                                    book.getPublished().toLowerCase().contains(search) ||
                                    book.getId().toLowerCase().contains(search) ||
                                    book.getPages().toLowerCase().contains(search) ||
                                    book.getCreatedOn().toLowerCase().contains(search)
                            ) {
                                List<Book> newBooks = new ArrayList<>();
                                if (filteredBooks.containsKey(book.getCategory())) {
                                    newBooks = filteredBooks.get(book.getCategory());
                                }
                                newBooks.add(book);
                                filteredBooks.put(book.getCategory(), newBooks);
                            }
                        }
                        recyclerViewAdapter.setCategoryBooks(filteredBooks);
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            case R.id.menu_create_book:
                Intent intent = new Intent(this, CreateBookActivity.class);
                this.startActivityForResult(intent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            getBooks();
        }
    }

    public void getBooks() {
        String API_URL = BuildConfig.API_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(BooksService.class).listBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                books.clear();
                categoryBooks.clear();
                books = response.body();
                for (Book book : books) {
                    List<Book> cBooks = categoryBooks.get(book.getCategory());
                    if (cBooks == null) {
                        List<Book> newBooks =  new ArrayList<>();
                        newBooks.add(book);
                        categoryBooks.put(book.getCategory(), newBooks);
                    } else {
                        cBooks.add(book);
                    }
                }

                recyclerViewAdapter.setCategoryBooks(categoryBooks);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }
}
