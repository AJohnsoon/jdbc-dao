package main.java.application.db.config;

import main.java.application.db.exceptions.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection(){

        if(connection == null){
            try{
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if(connection != null){
            try {
                connection.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResults(ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement){
        try{
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("./src/resources/config/db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
}
