package com.example.books;

import com.example.books.models.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BooksService {

    @GET("box_479f5c073a80294b4c3b")
    Call<List<Book>> listBooks();

    @POST("box_479f5c073a80294b4c3b")
    Call<Book> createBook(@Body Book book);

    @DELETE("box_479f5c073a80294b4c3b/{book_id}")
    Call<Book> deleteBook(@Path("book_id") String  book_id);
}
