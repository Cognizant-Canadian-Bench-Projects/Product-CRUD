package com.cognizant.productcrud.controller;

import com.cognizant.productcrud.models.Department;
import com.cognizant.productcrud.models.Product;
import com.cognizant.productcrud.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;


    @InjectMocks
    private ProductController productController;

    Product product1;
    Product product2;
    Product product3;
    Product product4;

    Department department1;
    Department department2;
    List<Product> productList;

    @BeforeEach
    public void init(){
        department1=new Department(1,"clothing");
        product1 = new Product(1,"shirt",department1);
        product2 = new Product(1,"pant",department1);
        product3 = new Product(1,"glove",department1);
        product4 = new Product(1,"cap",department1);
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

    }

    @Test
    void getProduct_test() {
        productController.getProduct("shirt");
        verify(productService,times(1)).findByName("shirt");

    }

    @Test
    void getProduct_assertionPositiveTest(){
        Department department = new Department(1,"clothing");
        Product shirt = new Product(1,"shirt",department);
        when(productService.findByName("shirt")).thenReturn(shirt);

        ResponseEntity actual= productController.getProduct("shirt");
        assertThat(actual.getStatusCodeValue()).isEqualTo(200);
        assertThat(actual.getBody()).isEqualTo(shirt);
    }

    @Test
    void getProduct_assertionNegativeTest(){
        when(productService.findByName("shirts")).thenThrow(EntityNotFoundException.class);

        ResponseEntity actual= productController.getProduct("shirts");
        assertThat(actual.getStatusCodeValue()).isEqualTo(404);

    }

    @Test
    void getAllProducts(){
        when(productService.getAllProducts()).thenReturn(productList);
        ResponseEntity actual= productController.getProduct("");
        assertThat(actual.getBody()).isEqualTo(productList);
    }
}