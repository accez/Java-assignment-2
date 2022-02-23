package com.example.javaassignment2.models.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicInterface<T> {
    List<T> selectRandom ();
}
