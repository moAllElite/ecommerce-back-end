package com.flyobs.ecommercebackend.repositories;

import com.flyobs.ecommercebackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.Optional;
import java.util.List;

public interface ProductRepository extends   JpaRepository<Product, BigInteger>{
    @Query(value = "SELECT p FROM Product  p  JOIN Category c   on c.id=p.category.id" +
            "  WHERE p.category.categoryName=:categoryName ")
    Optional<List<Product>> findByCategory(@RequestParam String categoryName);
    @Query("SELECT p FROM Product p WHERE p.name=:name")
   Optional<Product> findByName(String name);

}
