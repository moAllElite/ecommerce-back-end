package com.flyobs.ecommercebackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;
import java.util.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    private String categoryName;
    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;
}
