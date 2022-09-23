package main.java.application.model.dao;

import main.java.application.db.config.DB;
import main.java.application.model.impl.DepartmentDaoJDBC;
import main.java.application.model.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}