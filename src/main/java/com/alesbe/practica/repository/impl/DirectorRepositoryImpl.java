package com.alesbe.practica.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alesbe.practica.business.entity.Director;
import com.alesbe.practica.db.DBUtil;
import com.alesbe.practica.repository.DirectorRepository;

public class DirectorRepositoryImpl implements DirectorRepository {

    Connection connection = DBUtil.getConnection();

    @Override
    public List<Director> getAll() {
        List<Director> allDirectors = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM directors";

            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                allDirectors.add(
                    new Director(
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
        
        return allDirectors;
    }
    
}
