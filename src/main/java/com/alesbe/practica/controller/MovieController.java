package com.alesbe.practica.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alesbe.practica.business.service.ActorService;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.ActorServiceImpl;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movie")
public class MovieController {
    MovieService movieService = new MovieServiceImpl();
    ActorService actorService = new ActorServiceImpl();
    
    @GetMapping("{movieId}")
    public String getMovieById(Model model, @PathVariable("movieId") int movieId) {
        model.addAttribute("movie", this.movieService.getById(movieId));
        model.addAttribute("allActors", this.actorService.getAll());
        model.addAttribute("movieActors", this.actorService.getActoresByMovieId(movieId));
        model.addAttribute("director", this.movieService.getDirectorByMovieId(movieId));
        return "movie";
    }

    @PostMapping("/delete")
    public String deleteMovieById(HttpServletRequest httpServletRequest) {
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String title = httpServletRequest.getParameter("title");
        int year = Integer.parseInt(httpServletRequest.getParameter("year"));
        int runtime = Integer.parseInt(httpServletRequest.getParameter("runtime"));
        String name = httpServletRequest.getParameter("name");
        String[] actors = httpServletRequest.getParameterValues("actors");
        
        System.out.println(id);
        System.out.println(title);
        System.out.println(year);
        System.out.println(runtime);
        System.out.println(name);

        for (String actorId : actors) {
            System.out.println(actorId);
        }
        //System.out.println("[IMDB ID]: " + imdb_id);
        return "redirect:/movie/" + 1;
    }

    @PostMapping("/update")
    public String updateMovieById(Model model, @RequestParam("imdb_id") String imdb_id) {
        System.out.println("[IMDB ID]: " + imdb_id);
        return "redirect:/movie/" + 1;
    }
}