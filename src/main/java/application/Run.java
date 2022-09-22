package main.java.application;

import main.java.application.model.dao.DaoFactory;
import main.java.application.model.dao.SellerDao;
import main.java.application.model.entities.Department;
import main.java.application.model.entities.Seller;

import java.sql.SQLException;
import java.util.List;

public class Run {
    public static void main(String[] args) throws SQLException {
        System.out.println("_______FindByID_________");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller sellerId = sellerDao.findById(3);
        System.out.println(sellerId);
        System.out.println("________________");

        System.out.println("_______FindBuyDepartmentID_________");
        Department dp = new Department(3, null);
        List<Seller> listFindByDepartmentId = sellerDao.findByDepartment(dp);
        for (Seller obj: listFindByDepartmentId) {
            System.out.println(obj);
        }


        System.out.println("_______FindAll_________");
        List<Seller> listFindAll = sellerDao.findAll();
        for (Seller obj: listFindAll) {
            System.out.println(obj);
        }
    }
}