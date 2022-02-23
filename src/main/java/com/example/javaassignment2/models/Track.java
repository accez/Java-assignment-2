package com.example.javaassignment2.models;

public class Track {
    private int trackId;
    private String name;
    private String composer;

    public Track(int trackId, String name, String composer) {
        this.trackId = trackId;
        this.name = name;
        this.composer = composer;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}
