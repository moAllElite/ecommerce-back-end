package com.flyobs.ecommercebackend.controllers;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.services.ICategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/categories")
@EnableTransactionManagement
@RestController
public class CategoryController {
    private final ICategoryService categoryService;
    /**
     *  Get all categories GET http:localhost:8080/api/categories
     * @return list of categories
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> showAllCategories(){
        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Transactional
    public  ResponseEntity<Category> save(
          @Valid @RequestBody CategoryDto categoryDto
    ){
        return  ResponseEntity.ok(categoryService.saveCategory(categoryDto));
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
            categoryService
                    .getCategoryById(id)
        );
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<CategoryDto> update(
            @PathVariable BigInteger id,
           @Valid @RequestBody CategoryDto categoryDto
    ){
        Optional<CategoryDto> categoryFound = categoryService
                .getCategoryById(id);
        if(categoryFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        CategoryDto result = categoryService
                    .update(id,categoryDto);
        return  ResponseEntity.ok(result);


    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteById(
            @PathVariable BigInteger id
    ){
        categoryService.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
