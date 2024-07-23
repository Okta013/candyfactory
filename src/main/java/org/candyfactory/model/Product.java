package org.candyfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "calories")
    private int calories;
    @Column(name = "supplier_id")
    private int supplier;
    @Column(name = "composition")
    private String composition;
    @Column(name= "type_id")
    private int type;

    public Product() {}

    public Product(String name, double price, int calories, int supplier,
                   String composition, int type) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.supplier = supplier;
        this.composition = composition;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
