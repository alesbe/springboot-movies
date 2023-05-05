package com.alesbe.practica.repository;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Movie;

public interface MovieRepository {
    public List<Movie> getAll();
    public Movie getById(int movieId);
    public boolean insertMovie(Movie movie);
    public boolean updateMovie(int movieId);
    public boolean deleteMovie(int movieId);
    public List<Movie> getPaginatedMovies();
    public List<Movie> getSortedByYear();
    public List<Movie> getTopMovies(int ammount);
    public Movie getByIdWithDirectorName(int movieId);
    public List<Actor> getActoresByMovieId(int movieId);
}
