package com.alesbe.practica.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

@Controller
public class MainController {

    MovieService movieService = new MovieServiceImpl();
    
    @GetMapping("/")
    public String index(Model model) {
        List<Movie> allMovies = this.movieService.getAll();
        model.addAttribute("movies", allMovies);
        return "index";
    }

    @GetMapping("/sorted")
    public String sortByTitle(Model model) {
        List<Movie> allMovies = this.movieService.getAllSorted();
        model.addAttribute("movies", allMovies);
        return "index";
    }
}
