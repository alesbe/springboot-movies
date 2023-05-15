package com.alesbe.practica.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Director;
import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.business.service.ActorService;
import com.alesbe.practica.business.service.DirectorService;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.business.service.impl.ActorServiceImpl;
import com.alesbe.practica.business.service.impl.DirectorServiceImpl;
import com.alesbe.practica.business.service.impl.MovieServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/movie")
public class MovieController {
    MovieService movieService = new MovieServiceImpl();
    ActorService actorService = new ActorServiceImpl();
    DirectorService directorService = new DirectorServiceImpl();

    @GetMapping("/new")
    public String getAddMovie(Model model) {
        Director emptyDirector = new Director(0, null, null, 0, 0);
        Movie emptyMovie = new Movie(0, null, null, 0, 0);
        emptyMovie.setDirector(emptyDirector);

        model.addAttribute("movie", emptyMovie);
        model.addAttribute("allActors", this.actorService.getAll());
        model.addAttribute("movieActors", new ArrayList<Movie>());
        model.addAttribute("director", emptyDirector);
        model.addAttribute("allDirectors", this.directorService.getAll());

        model.addAttribute("isUpdatingMovie", false);

        return "movie";
    }

    @PostMapping("/new")
    public String postAddMovie(HttpServletRequest httpServletRequest) {
        // Get form parameters
        // Movie params
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String imdbId = httpServletRequest.getParameter("imdbId");
        String title = httpServletRequest.getParameter("title");
        int year = Integer.parseInt(httpServletRequest.getParameter("year"));
        int runtime = Integer.parseInt(httpServletRequest.getParameter("runtime"));

        // Director params
        int directorId = Integer.parseInt(httpServletRequest.getParameter("directorId"));

        // Actor params
        String[] actors = httpServletRequest.getParameterValues("actors");

        List<Actor> newActors = new ArrayList<>();
        for (String actorId : actors) {
            newActors.add(
                this.actorService.getActorById(Integer.parseInt(actorId))
            );
        }

        // Update movie
        Movie newMovie = new Movie(id, imdbId, title, year, runtime);
        newMovie.setDirector(this.directorService.getById(directorId));

        this.movieService.insertMovie(newMovie);

        // Update actors
        this.movieService.updateMovieActors(imdbId, newActors);

        return "redirect:/";
    }

    @GetMapping("{movieId}")
    public String getMovieById(Model model, @PathVariable("movieId") int movieId) {
        model.addAttribute("movie", this.movieService.getById(movieId));
        model.addAttribute("allActors", this.actorService.getAll());
        model.addAttribute("movieActors", this.actorService.getActoresByMovieId(movieId));
        model.addAttribute("director", this.movieService.getById(movieId).getDirector());
        model.addAttribute("allDirectors", this.directorService.getAll());

        model.addAttribute("isUpdatingMovie", true);

        return "movie";
    }

    @PostMapping("/delete")
    public String deleteMovieById(HttpServletRequest httpServletRequest) {
        String id = httpServletRequest.getParameter("id");
        movieService.deleteMovie(Integer.parseInt(id));

        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateMovieById(HttpServletRequest httpServletRequest) {
        // Get form parameters
        // Movie params
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String imdbId = httpServletRequest.getParameter("imdbId");
        String title = httpServletRequest.getParameter("title");
        int year = Integer.parseInt(httpServletRequest.getParameter("year"));
        int runtime = Integer.parseInt(httpServletRequest.getParameter("runtime"));

        // Director params
        int directorId = Integer.parseInt(httpServletRequest.getParameter("directorId"));

        // Actor params
        String[] actors = httpServletRequest.getParameterValues("actors");

        List<Actor> updatedActors = new ArrayList<>();
        for (String actorId : actors) {
            updatedActors.add(
                this.actorService.getActorById(Integer.parseInt(actorId))
            );
        }

        // Update movie
        Movie updatedMovie = new Movie(id, imdbId, title, year, runtime);
        updatedMovie.setDirector(this.directorService.getById(directorId));

        this.movieService.updateMovie(updatedMovie);

        // Update actors
        this.movieService.updateMovieActors(imdbId, updatedActors);

        return "redirect:/movie/" + id;
    }
}
