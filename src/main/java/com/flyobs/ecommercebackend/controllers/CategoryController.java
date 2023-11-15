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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("categories")
@RestController
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;
    /**
     *  Get all categories GET http:localhost:8080/api/categories
     * @return list of categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> showAllCategories(){
        return ResponseEntity.ok(
                categoryServiceImpl.getAllCategories()
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @Transactional
    public  ResponseEntity<BigInteger> save(
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
    @Transactional
    public  ResponseEntity<Optional<CategoryDto>> findById(
            @PathVariable BigInteger id
    ){
        return ResponseEntity.ok(
            categoryServiceImpl
                    .getCategoryById(id)
        );
    }
    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<Category> update(
            @PathVariable BigInteger id,
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
    @Transactional
    public ResponseEntity<Void> deleteById(
            @PathVariable BigInteger id
    ){
        categoryServiceImpl.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
