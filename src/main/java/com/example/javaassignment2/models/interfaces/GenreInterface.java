package com.example.javaassignment2.models.interfaces;

import com.example.javaassignment2.models.Genre;

import java.util.List;

public interface GenreInterface extends MusicInterface<Genre>{
    @Override
    List<Genre> selectRandom();
}
