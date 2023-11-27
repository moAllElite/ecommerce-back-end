package com.flyobs.ecommercebackend.services;
import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Product;
import org.springframework.data.domain.Page;
import java.math.BigInteger;
import java.util.*;

public interface IProductService {
    Product saveProduct(ProductDto productDto);
    Optional<ProductDto>  findById(BigInteger id);
    Page<ProductDto> paginatedAndSortingProducts(int pageSize, int index);
    void delete(BigInteger id);

}
