package com.flyobs.ecommercebackend.controllers;
import com.flyobs.ecommercebackend.dto.ProductDto;
import java.util.*;

import com.flyobs.ecommercebackend.entities.Product;
import com.flyobs.ecommercebackend.services.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
@RequiredArgsConstructor
@RequestMapping("products")
@RestController
public class ProductController {

    private final @NotNull IProductService productService;

    /**
     * Get all products GET localhost:8080/api/products?size=2&index=0&asc=true
     * asc is by default  at true
     */
    @GetMapping()
    public @NotNull ResponseEntity<Page<ProductDto>> showAllProducts(
            @RequestParam("index") int pageNumber,
            @RequestParam("size") int pageSize
    ){
        return    ResponseEntity.ok(
                 productService
                        .paginatedAndSortingProducts(pageSize, pageNumber)
        );
    }

    /**
     * Post  new product POST localhost:8080/api/products
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @NotNull  ResponseEntity<Product>  saveProduct(
            @RequestBody  ProductDto productDto
    ){
                if(productDto.getCategory().getCategoryName().equalsIgnoreCase("")){
                    return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(productService.saveProduct(productDto));
    }

    /**
     * Get method find by id ex:  localhost:8080/api/products/1
     */
    @GetMapping("/{id}")
    public @NotNull ResponseEntity<Optional<ProductDto>> findById(
            @PathVariable
            BigInteger id
    ){
        return  ResponseEntity.ok(
                productService.findById(id)
        );
    }

    /**
     * Delete  product DELETE ex localhost:8080/api/products/1
     */
    @DeleteMapping("/{id}")
    @Transactional
    public @NotNull ResponseEntity<Void>  deleteById(
        @PathVariable BigInteger  id
    ){
        productService.delete(id);
        return  ResponseEntity.accepted().build();
    }


}
