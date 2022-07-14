package com.cognizant.productcrud.unit.service;


import com.cognizant.productcrud.dao.ProductRepository;
import com.cognizant.productcrud.models.Department;
import com.cognizant.productcrud.models.Product;
import com.cognizant.productcrud.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

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
    void getProduct_Positive(){
        when(productRepository.findByName("pant")).thenReturn(product1);

        Product actual = productService.findByName("pant");
        assertThat(actual).isEqualTo(product1);
    }

    @Test
    void getProduct_Negative(){
        Assertions.assertThrows(EntityNotFoundException.class,() -> {
            productService.findByName("");
        });
    }

    @Test
    void getAllProducts(){
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> actual = productService.getAllProducts();
        assertThat(actual).isEqualTo(productList);
    }
}
