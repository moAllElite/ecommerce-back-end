package com.flyobs.ecommercebackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private BigInteger id;
    private String categoryName;
}
