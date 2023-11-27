package com.flyobs.ecommercebackend.mapping;


import org.mapstruct.Mapper;
import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;


@Mapper(componentModel = "spring",uses ={Category.class})
public interface CategoryMapping {
   // @Mapping(target = "id",source = "id")
    Category toCategoryEntity(CategoryDto categoryDto);
    CategoryDto fromCategoryEntity(Category category);
}
