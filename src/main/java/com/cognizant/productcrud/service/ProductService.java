package com.cognizant.productcrud.service;


import com.cognizant.productcrud.dao.ProductRepository;
import com.cognizant.productcrud.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findByName(String name){
        Product product =productRepository.findByName(name);
        if(product == null){
            throw new EntityNotFoundException(name + " does not exist");
        }
        return product;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
