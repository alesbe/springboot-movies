package com.alesbe.practica.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.db.DBUtil;
import com.alesbe.practica.repository.ActorRepository;

public class ActorRepositoryImpl implements ActorRepository {

    Connection connection = DBUtil.getConnection();

    @Override
    public List<Actor> getAll() {
        List<Actor> allActores = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM actors";

            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                allActores.add(
                    new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("imdb_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                    )
                );
            }
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
        }
        
        return allActores;
    }

    @Override
    public List<Actor> getActoresByMovieId(int movieId) {
        List<Actor> filteredActors = new ArrayList<>();

        try {
            String sql = "select a.* from actors a inner join actors_movies am on a.imdb_id = am.actor_id inner join movies m on m.imdb_id = am.movie_id where (m.id = ?);";
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(movieId));

            while (resultSet.next()) {
                filteredActors.add(
                    new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("imdb_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                    )
                );
            }

            return filteredActors;
        } catch (Exception e) {
            System.out.println("[ERROR]: No se ha podido ejecutar la consulta");
            throw new RuntimeException();
        }
    }
    
}
