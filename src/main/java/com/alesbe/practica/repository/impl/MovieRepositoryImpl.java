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
            String sql = "INSERT into imdb.movies VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            boolean result = DBUtil.insert(connection, sql, Arrays.asList(
                movie.getId(),
                movie.getImdbId(),
                movie.getTitle(),
                movie.getYear(),
                movie.getImage(),
                movie.getRuntime(),
                movie.getDescription(),
                movie.getDirectorId()
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
                movie.getDirectorId(),

                movie.getId() // where clause
            ));

            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + movie);

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
                        resultSet.getInt("runtime"),
                        resultSet.getString("director_id")
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

    @Override
    public Movie getByIdWithDirectorName(int movieId) {
        try {
            String sql = "SELECT m.*, d.id as dir_id, d.imdb_id as dir_imdb_id, d.name as dir_name, d.birthYear as dir_birthYear, d.deathYear as dir_deathYear FROM movies m join directors d on m.director_id = d.imdb_id where (m.id = ?)";
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(movieId));

            while (resultSet.next()) {
                System.out.println("Director name: " + resultSet.getString("dir_name"));

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
    public Director getDirectorByMovieId(int movieId) {
        try {
            String sql = "SELECT d.* FROM directors d join movies m on m.director_id = d.imdb_id where (m.id = ?)";
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(movieId));

            while (resultSet.next()) {

                return new Director(
                    resultSet.getInt("id"),
                    resultSet.getString("imdb_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("birthYear"),
                    resultSet.getInt("deathYear")
                );
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }

        throw new RuntimeException();
    }
}
