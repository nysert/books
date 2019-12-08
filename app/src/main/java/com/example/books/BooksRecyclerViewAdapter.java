package com.example.books;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.books.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksRecyclerViewAdapter.BookRecyclerViewHolder> {

    private List<Book> books;

    BooksRecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BooksRecyclerViewAdapter.BookRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_card, viewGroup, false);
        BooksRecyclerViewAdapter.BookRecyclerViewHolder viewHolder = new BooksRecyclerViewAdapter.BookRecyclerViewHolder(linearLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookRecyclerViewHolder bookRecyclerViewHolder, int i) {
        final Book book = books.get(i);
        String authorFullName = book.getAutor().getFirst_name() + " " + book.getAutor().getLast_name();
        bookRecyclerViewHolder.textViewTitle.setText(book.getTitle());
        bookRecyclerViewHolder.textViewAuthor.setText(authorFullName);
        bookRecyclerViewHolder.textViewCategory.setText(book.getCategory());
        Picasso.get().load(book.getImage_url()).into(bookRecyclerViewHolder.bookImage);
        bookRecyclerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookDetailsActivity.class);
                intent.putExtra("book", book);
                v.getContext().startActivity(intent);
            }
        });
        bookRecyclerViewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Deseas borrar este libro?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String API_URL = BuildConfig.API_URL;
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(API_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        retrofit.create(BooksService.class).deleteBook(book.getId()).enqueue(new Callback<Book>() {
                            @Override
                            public void onResponse(Call<Book> call, Response<Book> response) {
                                Toast.makeText(v.getContext(), "Libro se ha borrado", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Book> call, Throwable t) {
                                Toast.makeText(v.getContext(), "Libro no se pudo borrado", Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    void setBooks(List<Book> books) {
        this.books = books;
    }

    public static class BookRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewCategory;
        CardView cardView;
        ImageView bookImage;

        public BookRecyclerViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.list_item_card);
            textViewTitle = view.findViewById(R.id.list_item_title);
            textViewAuthor = view.findViewById(R.id.list_item_author);
            textViewCategory = view.findViewById(R.id.list_item_category);
            bookImage = view.findViewById(R.id.list_item_image);
        }
    }
}

