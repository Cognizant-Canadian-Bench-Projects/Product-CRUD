package com.cognizant.productcrud.service;


import com.cognizant.productcrud.dao.ProductRepository;
import com.cognizant.productcrud.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findByName(String name){
        return null;
    }
}
