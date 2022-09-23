package main.java.application.model.dao;

import main.java.application.db.config.DB;
import main.java.application.model.impl.DepartmentDaoJDBC;
import main.java.application.model.impl.SellerDaoJDBC;

import java.sql.SQLException;

public class DaoFactory {
    public static SellerDao createSellerDao() throws SQLException {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartamentDao() throws SQLException {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
