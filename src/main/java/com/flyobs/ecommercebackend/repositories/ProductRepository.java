package com.flyobs.ecommercebackend.repositories;


import com.flyobs.ecommercebackend.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.flyobs.ecommercebackend.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductRepository extends   JpaRepository<Product, BigInteger>{
    @Query(
            value = "SELECT * FROM product p  inner join  category c on c.id=p.id where c.category_name=:categoryName ",
      nativeQuery = true
    )

   Optional<ProductDto> findByCategory(@Param("categoryName") String categoryName);
    @Query("SELECT p FROM Product p WHERE p.name=:name")
   Optional<Product> findByName(@Param("name") String name);

}
