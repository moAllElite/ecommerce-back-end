package com.flyobs.ecommercebackend.controllers;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final IProductService productService;

    /**
     * Get all products GET localhost:8080/api/products?size=2&index=0&asc=false
     * asc is by default  at true
     */

    @GetMapping
    public ResponseEntity<Page<ProductDto>> showAllProducts(
            @RequestParam(value = "index",defaultValue = "0") int pageNumber,
            @RequestParam(value = "size",defaultValue = "3") int pageSize,
            @RequestParam(value = "asc",defaultValue = "true") boolean asc
    ){
        return    ResponseEntity.ok(
                 productService
                        .paginatedAndSortingProducts(pageSize, pageNumber,asc)
        );
    }

    /**
     * Post  new product POST localhost:8080/api/products
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BigInteger>  saveProduct(
            @RequestBody ProductDto productDto
    ){
                return ResponseEntity.ok(
                        productService.saveProduct(productDto)
                );
    }

    /**
     * Get method find by id ex:  localhost:8080/api/products/1
     */

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDto>> findById(
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public  ResponseEntity<Void>  deleteById(
        @PathVariable BigInteger  id
    ){
        productService.delete(id);
        return  ResponseEntity.accepted().build();
    }

    /**
     * Search product  by category name  localhost:8080/api/products/search-by-category?name=verre
     */

    @GetMapping("/search-by-category/{name}")

    public ResponseEntity<Optional<List<ProductDto>>> searchByCategoryName(
            @RequestParam(value = "name",defaultValue = "") String categoryName
    ){
        return  ResponseEntity.ok(
                productService.searchProductsByCategoryName(categoryName)
        );
    }

    /**
     * Seacrh product by product name Get localhost:8080/api/products/search-product-by?name=légos
     */

    @GetMapping("/search-product-by/{name}")
    public ResponseEntity<Optional<ProductDto>> searchByProductName(
            @RequestParam(name = "name") String name
    ){
        return ResponseEntity.ok(
          productService.searchByProductName(name)
        );
    }
}

