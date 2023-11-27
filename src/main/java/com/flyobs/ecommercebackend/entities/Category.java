package com.flyobs.ecommercebackend.entities;



import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Column(nullable = false,length = 250)
    private String categoryName;
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> products;
}
