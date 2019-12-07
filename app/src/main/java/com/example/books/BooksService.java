package com.example.books;

import com.example.books.models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksService {

    @GET("box_479f5c073a80294b4c3b")
    Call<List<Book>> listBooks();
}
