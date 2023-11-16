package com.flyobs.ecommercebackend.repositories;


import com.flyobs.ecommercebackend.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.flyobs.ecommercebackend.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductRepository extends   JpaRepository<Product, BigInteger>{
    @Query(nativeQuery = true,value = "from product as p and category as c where p.category.categoryName like c.categoryName% ")
    Optional<ProductDto> findByCategory(String categoryName);
    @Query(value = " from  product  p where p.name like %name%" ,nativeQuery = true)
    Optional<ProductDto> findByName(String name);
}
