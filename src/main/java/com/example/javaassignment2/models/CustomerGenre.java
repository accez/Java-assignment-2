package com.example.javaassignment2.models;

import java.util.ArrayList;
import java.util.List;

public class CustomerGenre{
    private Customer customer;
    private final List<String> genres = new ArrayList<>();

    public CustomerGenre(Customer customer) {
        this.customer = customer;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void addGenres(String genre) {
        this.genres.add(genre);
    }

    @Override
    public String toString() {
        return "CustomerGenre{" +
                "genres=" + genres +
                '}';
    }
}
