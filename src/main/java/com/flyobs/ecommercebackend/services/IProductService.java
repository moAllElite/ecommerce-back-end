package com.flyobs.ecommercebackend.services;

import com.flyobs.ecommercebackend.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    BigInteger saveProduct(ProductDto productDto);
    Optional<ProductDto> findById(BigInteger id);
    Page<ProductDto> paginatedAndSortingProducts(int pageSize, int index,boolean isAscending);
    void delete(BigInteger id);

    Optional<ProductDto> searchByProductName(String name);
    Optional<List<ProductDto>> searchProductsByCategoryName(String categoryName);

}
