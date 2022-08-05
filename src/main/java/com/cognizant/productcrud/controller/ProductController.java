package com.cognizant.productcrud.controller;


import com.cognizant.productcrud.models.Product;
import com.cognizant.productcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins = "${fems.url}", allowCredentials = "true")
//@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    @Cacheable(value = "products", key = "")
    public ResponseEntity<?> getProduct(@RequestParam(required = false) String name) {
        try {
            if (name == null || name.equals("")) {
                return ResponseEntity.ok(productService.getAllProducts());

            } else {
                Product product = productService.findByName(name);
                return ResponseEntity.ok(product);
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
