package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.mapping.CategoryMapping;
import com.flyobs.ecommercebackend.repositories.CategoryRepository;
import com.flyobs.ecommercebackend.services.ICategoryService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {
    private    CategoryRepository categoryRepository;
    private   CategoryMapping categoryMapping;


    @Override
    public List<CategoryDto> getAllCategories() {
        return  categoryRepository
                .findAll()
                .stream()
                .map(categoryMapping::fromCategoryEntity)
                .toList();
    }

    @Override
    public @NotNull Optional<CategoryDto> getCategoryById(@NotNull BigInteger id) {
        return categoryRepository
                .findById(id)
                .map(categoryMapping::fromCategoryEntity);
    }

    @Override
    public void delete(BigInteger id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category  saveCategory(CategoryDto categoryDto) {

        return categoryRepository.save(
                    categoryMapping.toCategoryEntity(categoryDto)

        );
    }

    @Override
    public CategoryDto update(BigInteger id,CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id)){
            return null;
        }
       categoryDto.setId(id);
        Category    category = categoryMapping.toCategoryEntity(categoryDto);

        return categoryMapping.fromCategoryEntity(
                categoryRepository.save(category)
        );
    }


}
