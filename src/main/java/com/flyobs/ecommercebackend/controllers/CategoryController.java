package com.flyobs.ecommercebackend.controllers;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.services.impl.CategoryServiceImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("categories")
@RestController
public class CategoryController {
    private final @NotNull CategoryServiceImpl categoryServiceImpl;
    /**
     *  Get all categories GET http:localhost:8080/api/categories
     * @return list of categories
     */
    @GetMapping
    public @NotNull ResponseEntity<List<CategoryDto>> showAllCategories(){
        return ResponseEntity.ok(
                categoryServiceImpl.getAllCategories()
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public @NotNull ResponseEntity<BigInteger> save(
          @Valid @RequestBody CategoryDto categoryDto
    ){
        return  ResponseEntity.ok(
                categoryServiceImpl
                        .saveCategory(categoryDto)
                        .getId()
        );
    }

    /**
     * localhost:8080/api/categories/2
     * @return a response of category by id
     */
    @GetMapping("/{id}")
    public @NotNull ResponseEntity<Optional<CategoryDto>> findById(
            @PathVariable @NotNull BigInteger id
    ){
        return ResponseEntity.ok(
            categoryServiceImpl
                    .getCategoryById(id)
        );
    }
    @PutMapping("/{id}")
    @Transactional
    public @NotNull ResponseEntity<Category> update(
            @PathVariable @NotNull BigInteger id,
           @Valid @RequestBody CategoryDto categoryDto
    ){
        Optional<CategoryDto> categoryFound = categoryServiceImpl
                .getCategoryById(id);
        if(categoryFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Category result = categoryServiceImpl
                    .update(categoryDto);
        return  ResponseEntity.ok(result);


    }
    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity<Void> deleteById(
            @PathVariable @NotNull BigInteger id
    ){
        categoryServiceImpl.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
