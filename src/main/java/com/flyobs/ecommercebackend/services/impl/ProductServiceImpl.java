package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.mapping.ProductMapping;
import com.flyobs.ecommercebackend.repositories.ProductRepository;
import com.flyobs.ecommercebackend.services.IProductService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final    ProductMapping productMapping;

    private final   ProductRepository productRepository;



    @Override
    public Optional<List<ProductDto>> searchProductsByCategoryName(@NotNull String categoryName) {
      return productRepository
              .findByCategory(categoryName)
              .map(
                      products -> {
                          products.forEach(
                                  product -> product.getCategory().getCategoryName().equals(categoryName)
                          );
                        return products.stream().map(productMapping::fromProductEntity).toList();
                      }

        );
    }

    @Override
    public Optional<ProductDto> searchByProductName(String name) {
        return productRepository
                .findByName(name)
                .stream()
                .map(productMapping::fromProductEntity)
                .filter(r -> name.equals(r.getName()))
                .findFirst();
    }

    @Override
    public BigInteger saveProduct(ProductDto productDto) {
        //convert entity  ->dto

        return productRepository.save(
                productMapping.toProductEntity(productDto)
        ).getId();
    }

    @Override
    public  Optional<ProductDto> findById(BigInteger id) {
        return productRepository
                .findById(id)
                .stream()
                .filter(p-> id.equals(p.getId()))
                .findFirst()
                .map(productMapping::fromProductEntity);
    }

    @Override
    public Page<ProductDto> paginatedAndSortingProducts(int pageSize, int pageNumber,boolean isAscending) {
        Sort sort = Sort.by("name");

        Pageable paging = PageRequest.of(pageNumber, pageSize,isAscending?sort.ascending():sort.descending());

        return productRepository
                .findAll(paging)
                .map(productMapping::fromProductEntity);
    }



    @Override
    public void delete(BigInteger id) {
        productRepository.deleteById(id);
    }


}
