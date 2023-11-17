package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Product;
import com.flyobs.ecommercebackend.mapping.ProductMapping;
import com.flyobs.ecommercebackend.repositories.ProductRepository;
import java.math.BigInteger;
import java.util.Optional;
import com.flyobs.ecommercebackend.services.IProductService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final @NotNull ProductMapping productMapping;

    private final @NotNull ProductRepository productRepository;



    @Override
    public Product  saveProduct(ProductDto productDto) {
        return  productRepository.save(
                productMapping.toProductEntity(productDto)
        );
    }




    @Override
    public @NotNull Optional<ProductDto> findById(@NotNull BigInteger id) {
        return productRepository
                .findById(id)
                .stream()
                .filter(p-> id.equals(p.getId()))
                .findFirst()
                .map(productMapping::fromProductEntity);
    }

    @Override
    public @NotNull Page<ProductDto> paginatedAndSortingProducts(int pageSize, int pageNumber) {
        Sort sort = Sort.by("name");

        Pageable paging = PageRequest.of(pageNumber, pageSize,sort);
        return  productRepository
                .findAll(paging)
                .map(productMapping::fromProductEntity);
    }



    @Override
    public void delete(@NotNull BigInteger id) {
        productRepository.deleteById(id);
    }


}
