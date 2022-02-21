package com.example.javaassignment2.models;

import java.util.ArrayList;
import java.util.List;

public class CustomerGenre extends Customer{
    private final List<String> genres =new ArrayList<>();

    public CustomerGenre(String customerID, String firstName, String lastName, String country, String postalCode, String phone, String email, String genre) {
        super(customerID, firstName, lastName, country, postalCode, phone, email);
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
