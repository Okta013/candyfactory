package org.candyfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="product_type")
public class ProductType {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    public ProductType() {}


    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
