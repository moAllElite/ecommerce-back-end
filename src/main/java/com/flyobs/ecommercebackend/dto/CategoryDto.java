package com.flyobs.ecommercebackend.dto;


import com.flyobs.ecommercebackend.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import  java.util.*;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer id;
    @NotNull(message = "The category shouldn't be null")
    @Min(value = 2)
    private String categoryName;
    private List<Product> product;
}
