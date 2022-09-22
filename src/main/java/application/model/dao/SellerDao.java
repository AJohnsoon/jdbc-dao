package main.java.application.model.dao;

import main.java.application.model.entities.Seller;

import java.util.List;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Seller id);
    Seller findById(Seller id);
    List<Seller> findAll();
}
