package com.alesbe.practica.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

@Controller
public class MainController {

    MovieService movieService = new MovieServiceImpl();
    
    @GetMapping("/")
    public String index() {
        List<Actor> actors = this.movieService.getActoresByMovieId(40);
        for (Actor actor : actors) {
            System.out.println(actor);
        }
        return "index";
    }
}
