package com.flyobs.ecommercebackend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "product")
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Column(nullable = false,length = 150)
    private  String sku;
    @Column(nullable = false,length = 250)
    private  String name;
    @Column(nullable = false,length = 250)
    private  String description;
    @Column(nullable = false,length = 250)
    private BigDecimal  unitPrice;
    @Column(length = 250)
    private String imageUrl;
    @Column(nullable = false,length = 250)
    private  boolean active;
    @Column(nullable = false,length = 250)
    private  int unitInStock;
    @CreationTimestamp
    private Date dateCreated;
    @UpdateTimestamp
    private Date lastUpdate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
