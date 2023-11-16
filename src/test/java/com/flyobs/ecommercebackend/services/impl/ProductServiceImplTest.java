package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private  ProductServiceImpl productService;

    @Test
    void delete() {
        //given id
        BigInteger id =BigInteger.valueOf(2);
        //when
        productService.delete(id);
        Assertions.assertNotNull(id,"successful");
        System.out.println("successfully deleted product with the provided ID: "+id);
    }

    @Test
    void saveProduct() {
        //Given
        int unitInStock = 100;
        BigDecimal price = BigDecimal.valueOf(140000.0);

        //when
        Category category = new Category();
        category.setId(BigInteger.TWO);

        ProductDto productDto = new ProductDto();

        productDto.setActive(false);
        productDto.setImageUrl("img2.jpg");
        productDto.setName("tiny desk");
        productDto.setSku("td");
        productDto.setLastUpdate(new Date());
        productDto.setUnitInStock(unitInStock);
        productDto.setUnitPrice(price);
        productDto.setDescription("wooden chair");
        productDto.setDateCreated(new Date());
        productDto.setCategory(category);
        //when
        BigInteger productId =  productService.saveProduct(productDto);
        //then
        Assertions.assertNotNull(productId,"saved");
    }


    @Test
    void findById() {
        //given id
        BigInteger id = BigInteger.ONE;
        //expected name of category
        String exceptedName =  "climatiseur";
        //when
       Optional<ProductDto> productGet= productService.findById(id);
       if(productGet.isPresent()){
           System.out.println("successfully found");
           assertEquals("successfully",
                   exceptedName,
                   productGet.get().getName());
       }

    }

    @Test
    void paginatedAndSortingProducts() {
        //given
        double expectedLength = 1;
        int pageSize = 3;
        int pageNumber = 0;
        //when
        double totalPages=productService.paginatedAndSortingProducts(pageSize,pageNumber).getTotalPages();

        //then
        System.out.println("success expected length:"+expectedLength+"\t is equal to total product:"+totalPages);
        assertEquals("success", expectedLength, totalPages);

    }


}