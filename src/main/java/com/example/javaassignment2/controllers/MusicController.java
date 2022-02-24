package com.example.javaassignment2.controllers;

import com.example.javaassignment2.models.Artist;
import com.example.javaassignment2.models.Genre;
import com.example.javaassignment2.models.Track;
import com.example.javaassignment2.models.interfaces.ArtistInterface;
import com.example.javaassignment2.models.interfaces.GenreInterface;
import com.example.javaassignment2.models.interfaces.TrackInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * REST endpoint returning 5 random genres
     * @return {@link List} of {@link Genre}
     */
    @GetMapping("genre/random")
    public List<Genre> getRandomGenre() {
        return genreInterface.selectRandom();
    }

    /**
     * REST endpoint returning 5 random artists
     * @return {@link List} of {@link Artist}
     */
    @GetMapping("artist/random")
    public List<Artist> getRandomArtist() {
        return artistInterface.selectRandom();
    }

    /**
     * REST endpoint returning 5 random tracks
     * @return {@link List} of {@link Track}
     */
    @GetMapping("track/random")
    public List<Track> getRandomTrack() {
        return trackInterface.selectRandom();
    }

    /**
     * REST endpoint returning list of tracks matching / partially matching the param
     * @param name - String value of the track name
     * @return {@link List}
     */
    @GetMapping("track/{name}")
    public List<Track> getTrackByName(@PathVariable String name) {
        return trackInterface.getTrackInformation(name);
    }
}
