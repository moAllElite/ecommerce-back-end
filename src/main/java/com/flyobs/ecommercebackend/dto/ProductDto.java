package com.flyobs.ecommercebackend.dto;




import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter@Setter
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
    private CategoryDto category;
}
