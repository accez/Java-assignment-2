package com.example.javaassignment2.view;

import com.example.javaassignment2.models.interfaces.ArtistInterface;
import com.example.javaassignment2.models.interfaces.GenreInterface;
import com.example.javaassignment2.models.interfaces.TrackInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicView {
    @Autowired
    ArtistInterface artistInterface;
    @Autowired
    TrackInterface trackInterface;
    @Autowired
    GenreInterface genreInterface;

    @GetMapping("/")
    public String view(
            Model model
    ) {
        model.addAttribute("artists",artistInterface.selectRandom());
        model.addAttribute("tracks", trackInterface.selectRandom());
        model.addAttribute("genres", genreInterface.selectRandom());
        return "musicList";
    }


}
