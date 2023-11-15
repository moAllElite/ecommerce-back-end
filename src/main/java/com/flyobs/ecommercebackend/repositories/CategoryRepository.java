package com.flyobs.ecommercebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyobs.ecommercebackend.entities.Category;

import java.math.BigInteger;
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {

}
