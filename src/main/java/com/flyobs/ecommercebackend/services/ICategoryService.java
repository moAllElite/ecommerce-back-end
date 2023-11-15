package com.flyobs.ecommercebackend.services;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;

import java.math.BigInteger;
import  java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(BigInteger id);
    void delete (BigInteger id);
    Category saveCategory(CategoryDto categoryDto);
    Category update(CategoryDto categoryDto);
}
