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
        boolean result = movieService.insertMovie(new Movie(224, "tt435fdgg", "asd", 1023, 190, "nm1701024"));
        if(result) {
            System.out.println("pelicula añadida");
        } else {
            System.out.println("no se ha podido :(");
        }
        return "index";
    }
}
