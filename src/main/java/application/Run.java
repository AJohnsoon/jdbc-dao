package main.java.application;

import main.java.application.model.entities.Department;
import main.java.application.model.entities.Seller;

import java.util.Date;

public class Run {
    public static void main(String[] args){
        Department dp = new Department(1, "Books");
        Seller seller = new Seller(1, "Alex Green", "alexgreen@teste.com", new Date(), 3000.00, dp);
        System.out.println(dp);
        System.out.println(seller);
    }
}
