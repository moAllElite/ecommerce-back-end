package com.flyobs.ecommercebackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    private  String sku;
    private  String name;
    private  String description;
    private BigDecimal  unitPrice;
    private String imageUrl;
    private  boolean active;
    private  int unitInStock;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date lastUpdate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
