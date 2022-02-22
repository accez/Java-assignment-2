package com.example.javaassignment2.models;

import java.util.ArrayList;
import java.util.List;

public class CustomerGenre{
    private int id;
    private String firstName;
    private String lastName;
    private final List<String> genres = new ArrayList<>();

    public CustomerGenre(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerGenre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
