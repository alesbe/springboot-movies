package com.alesbe.practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

@Controller
@RequestMapping("/movie")
public class MovieController {
    MovieService movieService = new MovieServiceImpl();
    
    @GetMapping("{movieId}")
    public String getMovieById(Model model, @PathVariable("movieId") int movieId) {
        model.addAttribute("movie", this.movieService.getById(movieId));
        model.addAttribute("actores", this.movieService.getActoresByMovieId(movieId));
        model.addAttribute("director", this.movieService.getDirectorByMovieId(movieId));
        return "movie";
    }
}
