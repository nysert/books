package com.example.books;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.books.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Book> books;

    RecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView cardView = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_card, viewGroup, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        final Book book = this.books.get(i);
        String authorFullName = book.getAutor().getFirst_name() + " " + book.getAutor().getLast_name();
        recyclerViewHolder.textViewTitle.setText(book.getTitle());
        recyclerViewHolder.textViewAuthor.setText(authorFullName);
        recyclerViewHolder.textViewCategory.setText(book.getCategory());
        Picasso.get().load(book.getImage_url()).into(recyclerViewHolder.bookImage);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    void setBooks(List<Book> books) {
        this.books = books;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewTitle;
        TextView textViewAuthor;
        TextView textViewCategory;
        ImageView bookImage;

        public RecyclerViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
            textViewTitle = cardView.findViewById(R.id.list_item_title);
            textViewAuthor = cardView.findViewById(R.id.list_item_author);
            textViewCategory = cardView.findViewById(R.id.list_item_category);
            bookImage = cardView.findViewById(R.id.list_item_image);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
