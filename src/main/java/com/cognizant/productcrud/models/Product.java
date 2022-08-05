package com.cognizant.productcrud.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true, length = 50)
    private String name;

    @OneToOne
    @JoinColumn(name = "department_id")//column name for the foreign key
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(department, product.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }
}
