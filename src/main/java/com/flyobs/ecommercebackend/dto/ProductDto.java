package com.flyobs.ecommercebackend.dto;


import com.flyobs.ecommercebackend.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private BigInteger id;
    
    private  String sku;
    
    private  String name;
    
    private  String description;
    
    private BigDecimal  unitPrice;
    
    private String imageUrl;

    private  boolean active;
    
    private  int unitInStock;

    private Date dateCreated;
    
    private Date lastUpdate;

    private Category category;

}
