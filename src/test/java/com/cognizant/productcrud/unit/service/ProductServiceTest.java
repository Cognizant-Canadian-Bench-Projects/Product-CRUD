package com.cognizant.productcrud.unit.service;


import com.cognizant.productcrud.dao.ProductRepository;
import com.cognizant.productcrud.models.Department;
import com.cognizant.productcrud.models.Product;
import com.cognizant.productcrud.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach

    public void init(){
        department1=new Department(1,"clothing");
        product1 = new Product(1,"shirt",department1);
        product2 = new Product(1,"shirt",department1);
        product3 = new Product(1,"shirt",department1);
        product4 = new Product(1,"shirt",department1);


    }
}
