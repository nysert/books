package com.example.books;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.books.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Map<String, List<Book>> categoryBooks;

    RecyclerViewAdapter(Map<String, List<Book>> categoryBooks) {
        this.categoryBooks = categoryBooks;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_list_item, viewGroup, false);
        return new RecyclerViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        List<String> categories = new ArrayList<>(categoryBooks.keySet());
        String category = categories.get(i);
        recyclerViewHolder.textViewCategoryTitle.setText(category);

        RecyclerView recyclerViewBooks = recyclerViewHolder.recyclerViewBooks;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerViewBooks.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewBooks.setLayoutManager(layoutManager);
        BooksRecyclerViewAdapter booksRecyclerViewAdapter = new BooksRecyclerViewAdapter(categoryBooks.get(category));
        recyclerViewBooks.setAdapter(booksRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return new ArrayList<>(categoryBooks.keySet()).size();
    }

    void setCategoryBooks(Map<String, List<Book>> categoryBooks) {
        this.categoryBooks = categoryBooks;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView textViewCategoryTitle;
        RecyclerView recyclerViewBooks;

        RecyclerViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            textViewCategoryTitle = view.findViewById(R.id.list_item_category_title);
            recyclerViewBooks = view.findViewById(R.id.list_item_recycler_books);
        }
    }
}
