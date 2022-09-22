package main.java.application;

import main.java.application.model.dao.DaoFactory;
import main.java.application.model.dao.SellerDao;
import main.java.application.model.entities.Seller;

import java.sql.SQLException;

public class Run {
    public static void main(String[] args) throws SQLException {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller sellerId = sellerDao.findById(3);
        System.out.println(sellerId);
    }
}
