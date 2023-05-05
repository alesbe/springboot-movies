package com.alesbe.practica.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Movie;
import com.alesbe.practica.db.DBUtil;
import com.alesbe.practica.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

    Connection connection = DBUtil.getConnection();

    @Override
    public List<Movie> getAll() {
        List<Movie> allMovies = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM movies";

            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                allMovies.add(
                    new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("imdb_id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime"),
                        resultSet.getString("director_id")
                    )
                );
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
        }
        
        return allMovies;
    }

    @Override
    public Movie getById(int movieId) {
        try {
            String sql = "SELECT * FROM movies WHERE (id = ?)";
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(movieId));

            while (resultSet.next()) {
                return new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("imdb_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getInt("runtime"),
                    resultSet.getString("director_id")
                );
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }

        throw new RuntimeException();
    }

    @Override
    public boolean insertMovie(Movie movie) {
        try {
            String sql = "INSERT into imdb.movies VALUES (?, ?, ?, ?, null, ?, null, ?)";
            boolean result = DBUtil.insert(connection, sql, Arrays.asList(
                movie.getId(),
                movie.getImdbId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime(),
                movie.getDirectorId()
            ));

            return result;
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateMovie(int movieId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMovie'");
    }

    @Override
    public boolean deleteMovie(int movieId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMovie'");
    }

    @Override
    public List<Movie> getPaginatedMovies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaginatedMovies'");
    }

    @Override
    public List<Movie> getSortedByYear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSortedByYear'");
    }

    @Override
    public List<Movie> getTopMovies(int ammount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopMovies'");
    }

    @Override
    public Movie getByIdWithDirectorName(int movieId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByIdWithDirectorName'");
    }

    @Override
    public List<Actor> getActoresByMovieId(int movieId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActoresByMovieId'");
    }
    
}
