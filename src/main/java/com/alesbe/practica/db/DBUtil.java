package com.alesbe.practica.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUtil {
    final static String DRIVER = "jdbc:mariadb";
    final static String URL = "localhost:3306";
    final static String DB = "imdb";
    final static String USER = "root";
    final static String PASSWORD = "root";
     
    static String connectionString = String.format("%s://%s/%s?user=%s&password=%s", DRIVER, URL, DB, USER, PASSWORD);
 
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(connectionString);            
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la bbdd");
        }
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexción con la bbdd");
        }
    }

    private static PreparedStatement setParameters(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(sql);
            if(values != null) {
                for(int i=0;i<values.size();i++) {
                    Object value=values.get(i);
                    preparedStatement.setObject(i+1,value);
                }
            }    
            return preparedStatement;                        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet select(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException();
                 
        }
    }

    public static boolean insert(Connection connection, String sql, List<Object> values) {    
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return (preparedStatement.executeUpdate()>0)?true:false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int update(Connection connection, String sql, List<Object> values) {    
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }    
    
    public static int delete(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
