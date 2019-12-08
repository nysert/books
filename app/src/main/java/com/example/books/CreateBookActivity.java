package com.example.books;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.books.models.Author;
import com.example.books.models.Book;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateBookActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputLayoutIsbn;
    private TextInputLayout textInputLayoutTitle;
    private TextInputLayout textInputLayoutAuthorFirstName;
    private TextInputLayout textInputLayoutAuthorLastName;
    private TextInputLayout textInputLayoutCategory;
    private TextInputLayout textInputLayoutPublisher;
    private TextInputLayout textInputLayoutPages;
    private TextInputLayout textInputLayoutDescription;
    private TextInputLayout textInputLayoutImageUrl;

    private EditText editTextIsbn;
    private EditText editTextTitle;
    private EditText editTextAuthorFirstName;
    private EditText editTextAuthorLastName;
    private EditText editTextCategory;
    private EditText editTextPublisher;
    private EditText editTextPages;
    private EditText editTextDescription;
    private EditText editTextImageUrl;

    private DatePicker datePickerReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);

        textInputLayoutIsbn = findViewById(R.id.create_book_input_layout_isbn);
        textInputLayoutTitle = findViewById(R.id.create_book_input_layout_title);
        textInputLayoutAuthorFirstName = findViewById(R.id.create_book_input_layout_author_first_name);
        textInputLayoutAuthorLastName = findViewById(R.id.create_book_input_layout_author_last_name);
        textInputLayoutCategory = findViewById(R.id.create_book_input_layout_category);
        textInputLayoutPublisher = findViewById(R.id.create_book_input_layout_publisher);
        textInputLayoutPages = findViewById(R.id.create_book_input_layout_pages);
        textInputLayoutDescription = findViewById(R.id.create_book_input_layout_description);
        textInputLayoutImageUrl = findViewById(R.id.create_book_input_layout_image_url);

        editTextIsbn = findViewById(R.id.create_book_edit_text_isbn);
        editTextTitle = findViewById(R.id.create_book_edit_text_title);
        editTextAuthorFirstName = findViewById(R.id.create_book_edit_text_author_first_name);
        editTextAuthorLastName = findViewById(R.id.create_book_edit_text_author_last_name);
        editTextCategory = findViewById(R.id.create_book_edit_text_category);
        editTextPublisher = findViewById(R.id.create_book_edit_text_publisher);
        editTextPages = findViewById(R.id.create_book_edit_text_pages);
        editTextDescription = findViewById(R.id.create_book_edit_text_description);
        editTextImageUrl = findViewById(R.id.create_book_edit_text_image_url);

        datePickerReleaseDate = findViewById(R.id.create_book_date_picker_release_date);

        Button button = findViewById(R.id.create_book_button_create);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (hasValidFields()) {
            Book newBook = new Book();
            newBook.setIsbn(editTextIsbn.getText().toString());
            newBook.setTitle(editTextTitle.getText().toString());
            Author author = new Author(editTextAuthorFirstName.getText().toString(), editTextAuthorLastName.getText().toString());
            newBook.setAutor(author);
            newBook.setCategory(editTextCategory.getText().toString());
            newBook.setPublished(editTextPublisher.getText().toString());
            newBook.setPublished(editTextPages.getText().toString());
            newBook.setDescription(editTextDescription.getText().toString());
            newBook.setImage_url(editTextImageUrl.getText().toString());
            int day = datePickerReleaseDate.getDayOfMonth();
            int month = datePickerReleaseDate.getMonth();
            int year =  datePickerReleaseDate.getYear();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            newBook.setPublished(calendar.getTime().toString());

            String API_URL = BuildConfig.API_URL;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofit.create(BooksService.class).createBook(newBook).enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if (response.code() == 200) {
                        finish();
                    } else {
                        Toast toast =  Toast.makeText(getApplication(), "Error al Crear Libro", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    Toast toast =  Toast.makeText(getApplication(), "Error al Crear Libro", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        } else {
            Toast toast =  Toast.makeText(getApplication(), "Hay errores en los campos", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private boolean hasValidFields() {
        boolean isValid = true;
        if (editTextIsbn.getText().toString().length() < 10) {
            textInputLayoutIsbn.setError("Debe contener por lo menos 10 caracters");
            isValid = false;
        }
        if (editTextIsbn.getText().toString().matches("^[0-9]")) {
            textInputLayoutIsbn.setError("Solo puede contener numeros");
            isValid = false;
        }
        if (editTextTitle.getText().toString().length() == 0) {
            textInputLayoutTitle.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextAuthorFirstName.getText().toString().length() == 0) {
            textInputLayoutAuthorFirstName.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextAuthorLastName.getText().toString().length() == 0) {
            textInputLayoutAuthorLastName.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextCategory.getText().toString().length() == 0) {
            textInputLayoutCategory.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextPublisher.getText().toString().length() == 0) {
            textInputLayoutPublisher.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextPages.getText().toString().length() == 0) {
            textInputLayoutPages.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextDescription.getText().toString().length() == 0) {
            textInputLayoutDescription.setError("No puede estar vacio");
            isValid = false;
        }
        if (editTextImageUrl.getText().toString().length() == 0) {
            textInputLayoutImageUrl.setError("No puede estar vacio");
            isValid = false;
        }
        return isValid;
    }

}
