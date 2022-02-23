package com.example.javaassignment2.models.interfaces;

import com.example.javaassignment2.models.Artist;

import java.util.List;

public interface ArtistInterface extends MusicInterface<Artist> {
    @Override
    List<Artist> selectRandom();
}
