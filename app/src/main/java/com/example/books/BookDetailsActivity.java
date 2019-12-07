package com.example.books;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.books.models.Book;
import com.squareup.picasso.Picasso;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        ImageView imageView = findViewById(R.id.book_details_image);
        TextView textViewTitle = findViewById(R.id.book_details_title);
        TextView textViewAuthor = findViewById(R.id.book_details_author);
        TextView textViewCategory = findViewById(R.id.book_details_category);
        TextView textViewReleaseDate = findViewById(R.id.book_details_release_date);
        TextView textViewPages = findViewById(R.id.book_details_pages);
        TextView textViewIsbn = findViewById(R.id.book_details_isbn);
        TextView textViewDescription = findViewById(R.id.book_details_description);

        Book book = (Book)getIntent().getSerializableExtra("book");
        String fullAuthorName = book.getAutor().getFirst_name() + " " + book.getAutor().getLast_name();

        Picasso.get().load(book.getImage_url()).into(imageView);
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(fullAuthorName);
        textViewCategory.setText(book.getCategory());
        textViewReleaseDate.setText(book.getPublished());
        textViewPages.setText(book.getPages());
        textViewIsbn.setText(book.getIsbn());
        textViewDescription.setText(book.getDescription());
    }

}
