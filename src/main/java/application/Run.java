package main.java.application;

import main.java.application.model.dao.DaoFactory;
import main.java.application.model.dao.SellerDao;
import main.java.application.model.entities.Department;
import main.java.application.model.entities.Seller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Run {
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) throws SQLException, ParseException {
        System.out.println("_______FindByID_________");

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller sellerId = sellerDao.findById(3);
        System.out.println(sellerId);

        System.out.println();
        System.out.println("_______FindByDepartmentID_________");
        Department dp = new Department(4, null);
        List<Seller> listFindByDepartmentId = sellerDao.findByDepartment(dp);
        for (Seller obj: listFindByDepartmentId) {
            System.out.println(obj);
        }

        System.out.println();
        System.out.println("_______FindAll_________");
        List<Seller> listFindAll = sellerDao.findAll();
        for (Seller obj: listFindAll) {
            System.out.println(obj);
        }

        Date date = sdf.parse("27/03/2002");
        System.out.println("_______Insert Seller_________");
        Seller insertSeller = new Seller(null, "John Teal 2", "johnt@teste.com", date, 1827.75, dp);
        sellerDao.insert(insertSeller);
        System.out.println("Inserted! New id is: "+ insertSeller.getId());

        System.out.println("_______Update Seller_________");
        sellerId = sellerDao.findById(17);
        sellerId.setName("Martha White");
        sellerId.setEmail("marthawhite@test.com");
        sellerDao.update(sellerId);

        System.out.println("________DeleteSeller___________");
        sellerDao.deleteById(16);
    }
}