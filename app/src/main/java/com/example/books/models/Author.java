package com.example.books.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Author implements Serializable {

    @SerializedName("first_name")
    String first_name;

    @SerializedName("last_name")
    String last_name;

    public Author(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
