package com.example.javaassignment2.models.interfaces;

import com.example.javaassignment2.models.Track;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TrackInterface extends MusicInterface<Track> {
    @Override
    List<Track> selectRandom();

    List<Track>getTrackInformation(String trackName);
}
