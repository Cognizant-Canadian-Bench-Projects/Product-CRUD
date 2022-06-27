package com.cognizant.productcrud.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && departmentId == product.departmentId && Objects.equals(name, product.name) && Objects.equals(departmentName, product.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentId, departmentName);
    }
}
