package org.candyfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Supplier {
    @Id
    private int id;
    private String name;
    private String address;

    @OneToOne(optional=false, mappedBy="supplier")
    private Owner owner;

    @OneToMany(mappedBy="supplier")
    private Set<Product> products;

}
