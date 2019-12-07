package com.example.books.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("pages")
    private String pages;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("author")
    private Author autor;

    @SerializedName("isbn")
    private String isbn;

    @SerializedName("description")
    private String description;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("published")
    private String published;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    @SerializedName("_createdOn")
    private String createdOn;

    public Book() {
    }

    public Book(String id, String pages, String image_url, Author autor, String isbn, String description, String publisher, String published, String title, String category, String createdOn) {
        this.id = id;
        this.pages = pages;
        this.image_url = image_url;
        this.autor = autor;
        this.isbn = isbn;
        this.description = description;
        this.publisher = publisher;
        this.published = published;
        this.title = title;
        this.category = category;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Author getAutor() {
        return autor;
    }

    public void setAutor(Author autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
