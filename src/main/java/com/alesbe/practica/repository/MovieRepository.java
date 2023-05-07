package com.alesbe.practica.repository;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Director;
import com.alesbe.practica.business.entity.Movie;

public interface MovieRepository {
    public List<Movie> getAll();
    public Movie getById(int movieId);
    public boolean insertMovie(Movie movie);
    public boolean updateMovie(Movie movie);
    public boolean deleteMovie(int movieId);
    public List<Movie> getPaginatedMovies();
    public List<Movie> getSortedByYear();
    public List<Movie> getTopMovies(int limit);
    public Movie getByIdWithDirectorName(int movieId);
    public Director getDirectorByMovieId(int movieId);
}
