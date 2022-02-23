package com.example.javaassignment2.controllers;

import com.example.javaassignment2.models.Artist;
import com.example.javaassignment2.models.Genre;
import com.example.javaassignment2.models.Track;
import com.example.javaassignment2.models.interfaces.ArtistInterface;
import com.example.javaassignment2.models.interfaces.GenreInterface;
import com.example.javaassignment2.models.interfaces.TrackInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicController {
    @Autowired
    GenreInterface genreInterface;

    @Autowired
    ArtistInterface artistInterface;

    @Autowired
    TrackInterface trackInterface;

    @GetMapping("genre/random")
    public List<Genre> getRandomGenre(){
        return genreInterface.selectRandom();
    }

    @GetMapping("artist/random")
    public List<Artist> getRandomArtist(){
        return artistInterface.selectRandom();
    }
    @GetMapping("track/random")
    public List<Track> getRandomTrack(){
        return trackInterface.selectRandom();
    }
}
