package com.example.javaassignment2.view;

import com.example.javaassignment2.models.interfaces.TrackInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchView {
    @Autowired
    TrackInterface trackInterface;

    @GetMapping("/search")
    public String view(
            Model model,
            @RequestParam(defaultValue = "balls") String name
    ) {
        model.addAttribute("name", name);
        model.addAttribute("trackInfo",trackInterface.getTrackInformation(name));
        return "searchTrack";
    }
}
