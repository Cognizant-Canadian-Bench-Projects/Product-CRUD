package com.cognizant.productcrud.controller;


import com.cognizant.productcrud.models.Product;
import com.cognizant.productcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getProduct(@RequestParam String name){
        try {
            Product product = productService.findByName(name);
            return ResponseEntity.ok(product);
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }
}
