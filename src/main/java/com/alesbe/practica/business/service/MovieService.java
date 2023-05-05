package com.alesbe.practica.business.service;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Movie;

public interface MovieService {
    public List<Movie> getAll();
    public Movie getById(int movieId);
    public boolean insertMovie(Movie movie);
    public boolean updateMovie(Movie movie);
    public boolean deleteMovie(int movieId);
    public List<Movie> getPaginatedMovies();
    public List<Movie> getSortedByYear();
    public List<Movie> getTopMovies(int ammount);
    public Movie getByIdWithDirectorName(int movieId);
    public List<Actor> getActoresByMovieId(int movieId);
}
