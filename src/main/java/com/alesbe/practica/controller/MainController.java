package com.alesbe.practica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

@Controller
public class MainController {

    MovieService movieService = new MovieServiceImpl();
    
    @GetMapping("/")
    public String index() {
        boolean result = this.movieService.deleteMovie(224);

        if(result) {
            System.out.println("pelicula añadida");
        } else {
            System.out.println("no se ha podido :(");
        }
        return "index";
    }
}
