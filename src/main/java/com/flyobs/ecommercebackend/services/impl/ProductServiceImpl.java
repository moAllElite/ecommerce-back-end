package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.mapping.ProductMapping;
import com.flyobs.ecommercebackend.repositories.ProductRepository;

import java.math.BigInteger;
import java.util.Optional;
import com.flyobs.ecommercebackend.services.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

    private final    ProductMapping productMapping;

    private final   ProductRepository productRepository;

    public ProductServiceImpl(ProductMapping productMapping, ProductRepository productRepository) {
        this.productMapping = productMapping;
        this.productRepository = productRepository;
    }

    @Override
    public BigInteger saveProduct(ProductDto productDto) {
        return  productRepository.save(
                productMapping.toProductEntity(productDto)
        ).getId();
    }
    @Override
    public Optional<ProductDto> findById(BigInteger id) {
        return productRepository
                .findById(id)
                .stream()
                .filter(p-> id.equals(p.getId()))
                .findFirst()
                .map(productMapping::fromProductEntity);
    }

    @Override
    public Page<ProductDto> paginatedAndSortingProducts(int pageSize, int pageNumber) {
        Sort sort = Sort.by("name");

        Pageable paging = PageRequest.of(pageNumber, pageSize,sort);
        return  productRepository
                .findAll(paging)
                .map(productMapping::fromProductEntity);
    }





    @Override
    public void delete(BigInteger id) {
        productRepository.deleteById(id);
    }
}
