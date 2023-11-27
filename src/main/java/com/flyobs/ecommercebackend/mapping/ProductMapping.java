package com.flyobs.ecommercebackend.mapping;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.entities.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",uses ={Category.class})
public interface ProductMapping {
    Product toProductEntity(ProductDto productDto);

   ProductDto fromProductEntity(Product product);
}
