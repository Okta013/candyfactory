package org.candyfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner {
    @Id
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    @OneToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;
}
