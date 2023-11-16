package com.flyobs.ecommercebackend.controllers;
import com.flyobs.ecommercebackend.dto.ProductDto;
import java.util.*;
import com.flyobs.ecommercebackend.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
@RequiredArgsConstructor
@RequestMapping("products")
@RestController
public class ProductController {

    private final IProductService productService;

    /**
     * Get all products GET localhost:8080/api/products?size=2&index=0&asc=true
     * asc is by default  at true
     */
    @GetMapping()
    public ResponseEntity<Page<ProductDto>> showAllProducts(
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
    public ResponseEntity<BigInteger>  saveProduct(
            @RequestBody ProductDto productDto
    ){
                return ResponseEntity.ok(productService.saveProduct(productDto));
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
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void>  deleteById(
        @PathVariable BigInteger  id
    ){
        productService.delete(id);
        return  ResponseEntity.accepted().build();
    }

    /**
     * Search product  by category name  localhost:8080/api/products/book&index=0
     */
    @GetMapping("/{categoryName}")

    public ResponseEntity<Optional<List<ProductDto>>> searchByCategoryName(
            @RequestParam(value = "category",defaultValue = "") String categoryName,
            @RequestParam(defaultValue = "6",value = "size",required = false)int size,
            @RequestParam(defaultValue = "0",required = false) int index
    ){
        return  ResponseEntity.ok(
                productService.searchProductsByCategoryName(categoryName)
        );
    }
}
