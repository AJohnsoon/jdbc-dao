package main.java.application.model.dao;

import main.java.application.db.config.DB;
import main.java.application.model.impl.SellerDaoJDBC;

import java.sql.SQLException;

public class DaoFactory {
    public static SellerDao createSellerDao() throws SQLException {
        return new SellerDaoJDBC(DB.getConnection());
    }
}


/*
    SELECT seller. *,department.Name as DepName FROM seller INNER JOIN department
    ON seller.DepartmentId = department.Id WHERE seller.Id = ?
 */