package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.mapping.CategoryMapping;
import com.flyobs.ecommercebackend.repositories.CategoryRepository;
import com.flyobs.ecommercebackend.services.ICategoryService;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final   CategoryMapping categoryMapping;
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapping categoryMapping) {
        this.categoryRepository = categoryRepository;
        this.categoryMapping = categoryMapping;
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        return  categoryRepository
                .findAll()
                .stream()
                .map(categoryMapping::fromCategoryEntity)
                .toList();
    }

    @Override
    public Optional<CategoryDto> getCategoryById(BigInteger id) {
        return categoryRepository
                .findById(id)
                .map(categoryMapping::fromCategoryEntity);
    }

    @Override
    public void delete(BigInteger id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category saveCategory(CategoryDto categoryDto) {
        return categoryRepository.save(
                categoryMapping.toCategoryEntity(categoryDto)
        );
    }

    @Override
    public Category update(CategoryDto categoryDto) {
        return categoryRepository.save(
                categoryMapping.toCategoryEntity(categoryDto)
        );
    }


}
