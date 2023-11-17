package com.flyobs.ecommercebackend.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.flyobs.ecommercebackend.entities.Product;
import java.math.BigInteger;

public interface ProductRepository extends    JpaRepository<Product, BigInteger>{

}
