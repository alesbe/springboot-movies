package com.alesbe.practica.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.entity.Director;
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
                        resultSet.getInt("runtime")
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
            //String sql = "SELECT * FROM movies WHERE (id = ?)";
            String sql = """
                SELECT 
                m.*,
                d.id AS director_index,
                d.name AS director_name,
                d.birthyear AS director_birthyear, 
                d.deathyear AS director_deathyear 
                FROM movies m 
                INNER JOIN directors d ON m.director_id = d.imdb_id
                WHERE m.id = ?
            """;
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(movieId));

            while (resultSet.next()) {
                Director director = new Director(
                    resultSet.getInt("director_index"),
                    resultSet.getString("director_id"), 
                    resultSet.getString("director_name"),
                    resultSet.getInt("director_birthyear"),
                    resultSet.getInt("director_deathyear")
                );

                Movie movie = new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("imdb_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getInt("runtime")
                );

                
                movie.setDirector(director);
                
                return movie;
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
            String sql = "INSERT INTO movies (imdb_id, title, year, runtime, director_id) VALUES (?, ?, ?, ?, ?)";
            boolean result = DBUtil.insert(connection, sql, Arrays.asList(
                movie.getImdbId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getRuntime(),
                movie.getDirector().getImdb_id()
            ));

            return result;
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try {
            String sql = "UPDATE movies set id = ?, imdb_id = ?, title = ?, year = ?, image = ?, runtime = ?, description = ?, director_id = ? WHERE (id = ?)";
            System.out.println("MI PELI ESTA AQUI: " + movie);
            int result = DBUtil.update(connection, sql, Arrays.asList(
                movie.getId(),
                movie.getImdbId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getImage(),
                movie.getRuntime(),
                movie.getDescription(),
                movie.getDirector().getImdb_id(),

                movie.getId() // where clause
            ));

            return (result >= 1) ? true : false; 
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }
    }

    @Override
    public boolean deleteMovie(int movieId) {
        try {
            String sql = "DELETE FROM movies WHERE (id = ?)";
            int result = DBUtil.delete(connection, sql, Arrays.asList(movieId));

            return (result >= 1) ? true : false; 
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }
    }

    @Override
    public boolean updateMovieActors(String imdbId, List<Actor> actors) {
        // 1.- Get all actors_movies rows by movieId and delete them
        try {
            String sql = "DELETE FROM actors_movies WHERE (movie_id = ?)";
            int result = DBUtil.delete(connection, sql, Arrays.asList(imdbId));

        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }

        // 2.- Insert actors in a for loop until all actors are filled. Get first imdbId using movieId.
        for (Actor actor : actors) {
            try {
                String sql = "INSERT INTO actors_movies (movie_id, actor_id) VALUES (?, ?);";
                boolean result = DBUtil.insert(connection, sql, Arrays.asList(imdbId, actor.getImdbId()));
    
            } catch (Exception e) {
                System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
                throw new RuntimeException();
            }
        }
        return true;
    }

    @Override
    public List<Movie> getPaginatedMovies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaginatedMovies'");
    }

    @Override
    public List<Movie> getSortedByYear() {
        List<Movie> allMoviesSorted = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM movies ORDER BY year DESC";

            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                allMoviesSorted.add(
                    new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("imdb_id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                    )
                );
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
        }
        
        return allMoviesSorted;
    }

    @Override
    public List<Movie> getTopMovies(int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopMovies'");
    }
}
