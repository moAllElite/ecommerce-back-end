package com.flyobs.ecommercebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyobs.ecommercebackend.entities.Category;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface CategoryRepository extends JpaRepository<Category, BigInteger> {

}
