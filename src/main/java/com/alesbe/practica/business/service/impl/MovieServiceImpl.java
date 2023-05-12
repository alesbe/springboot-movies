package com.alesbe.practica.business.service.impl;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Director;
import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.business.service.MovieService;
import com.alesbe.practica.repository.MovieRepository;
import com.alesbe.practica.repository.impl.MovieRepositoryImpl;

public class MovieServiceImpl implements MovieService {

    MovieRepository repository = new MovieRepositoryImpl();

    @Override
    public List<Movie> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Movie getById(int movieId) {
        return this.repository.getById(movieId);
    }

    @Override
    public boolean insertMovie(Movie movie) {
        return this.repository.insertMovie(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return this.repository.updateMovie(movie);
    }

    @Override
    public boolean deleteMovie(int movieId) {
        return this.repository.deleteMovie(movieId);
    }

    @Override
    public boolean updateMovieActors(int movieId, List<Actor> actors) {
        return this.repository.updateMovieActors(movieId, actors);
    }

    @Override
    public List<Movie> getPaginatedMovies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaginatedMovies'");
    }

    @Override
    public List<Movie> getSortedByYear() {
        return this.repository.getSortedByYear();
    }

    @Override
    public List<Movie> getTopMovies(int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopMovies'");
    }
}
