package org.candyfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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

    @ManyToMany
    @JoinTable (name="product_type_product",
            joinColumns=@JoinColumn (name="product_type_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    public ProductType() {}


    public ProductType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
