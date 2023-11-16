package com.flyobs.ecommercebackend.services;
import com.flyobs.ecommercebackend.dto.ProductDto;
import org.springframework.data.domain.Page;
import java.math.BigInteger;
import java.util.*;

public interface IProductService {
    BigInteger saveProduct(ProductDto productDto);
    Optional<ProductDto>  findById(BigInteger id);
    Page<ProductDto> paginatedAndSortingProducts(int pageSize, int index);
    void delete(BigInteger id);
    Optional<List<ProductDto>> searchProductsByCategoryName(String categoryName);
    Optional<ProductDto> searchByProductName(String name);
}
