package com.flyobs.ecommercebackend.mapping;

import org.mapstruct.Mapper;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;

@Mapper
public interface CategoryMapping {
   
    Category toCategoryEntity(CategoryDto categoryDto);
    CategoryDto fromCategoryEntity(Category category);
}
