package com.cognizant.productcrud.controller;


import com.cognizant.productcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${fems.url}", allowCredentials = "true")
public class ProductController {
    @Autowired
    private ProductService productService;


}
