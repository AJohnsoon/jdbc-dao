package main.java.application.model.impl;

import main.java.application.db.config.DB;
import main.java.application.db.exceptions.DbException;
import main.java.application.model.dao.DepartmentDao;
import main.java.application.model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;
    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement("INSERT INTO department " +
                    "(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closePreparedStatement(preparedStatement);
            DB.closeResults(resultSet);
        }

    }

    @Override
    public void update(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected < 1){
                throw new DbException("ID: "+obj.getId()+ " not found in database!");
            }

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected < 1){
               throw new DbException("ID: "+id+ " not found in database!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return instantiateDepartment(resultSet);
            }
            else{
                throw new DbException("Error: ID: "+id + " not found!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closePreparedStatement(preparedStatement);
            DB.closeResults(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT * from department");
            resultSet = preparedStatement.executeQuery();
            List<Department> departmentList = new ArrayList<>();
            while (resultSet.next()){
                Department depart = instantiateDepartment(resultSet);
                departmentList.add(depart);
            }
            return departmentList;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closePreparedStatement(preparedStatement);
            DB.closeResults(resultSet);
        }
    }
    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));
        return department;
    }

}
