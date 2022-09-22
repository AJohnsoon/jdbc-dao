package main.java.application.model.impl;

import main.java.application.db.config.DB;
import main.java.application.db.exceptions.DbException;
import main.java.application.model.dao.SellerDao;
import main.java.application.model.entities.Department;
import main.java.application.model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private final Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {
    }

    @Override
    public void update(Seller obj) {
    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller " +
                    "INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                return instantiateSeller(resultSet, department);
            }
            return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResults(resultSet);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId ORDER BY Name");
            resultSet = preparedStatement.executeQuery();

            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()){

                Department depart = map.get(resultSet.getInt("DepartmentId"));

                if(depart == null){
                    depart = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), depart);
                }
                sellerList.add(instantiateSeller(resultSet, depart));
            }
            return sellerList;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResults(resultSet);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name");
            preparedStatement.setInt(1, department.getId());
            resultSet = preparedStatement.executeQuery();

            List<Seller> sellerList = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (resultSet.next()){

                Department depart = map.get(resultSet.getInt("DepartmentId"));

                if(depart == null){
                    depart = instantiateDepartment(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), depart);
                }
                sellerList.add(instantiateSeller(resultSet, depart));
            }
            return sellerList;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResults(resultSet);
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller obj = new Seller();
        obj.setId(resultSet.getInt("Id"));
        obj.setName(resultSet.getString("Name"));
        obj.setEmail(resultSet.getString("Email"));
        obj.setBaseSalary(resultSet.getDouble("BaseSalary"));
        obj.setBirthDate(resultSet.getDate("BirthDate"));
        obj.setDepartment(department);
        return obj;
    }

}
