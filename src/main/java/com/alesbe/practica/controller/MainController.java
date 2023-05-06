package com.alesbe.practica.controller;

import java.util.List;

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
        Movie result = this.movieService.getByIdWithDirectorName(1);
        System.out.println(result);
        return "index";
    }
}
