package com.flyobs.ecommercebackend.mapping;

import org.mapstruct.Mapper;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Product;



@Mapper
public interface ProductMapping {

    Product toProductEntity(ProductDto productDto);

    ProductDto fromProductEntity(Product product);
}
