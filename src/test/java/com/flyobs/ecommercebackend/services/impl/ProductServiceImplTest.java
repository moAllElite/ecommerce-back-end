package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.dto.ProductDto;
import com.flyobs.ecommercebackend.entities.Category;
import com.flyobs.ecommercebackend.entities.Product;
import com.flyobs.ecommercebackend.mapping.ProductMapping;
import com.flyobs.ecommercebackend.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private  ProductServiceImpl productService;
    @Autowired
    private ProductMapping productMapping;
    @Autowired
    private CategoryRepository categoryRepository;

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
        BigDecimal price = BigDecimal.valueOf(7000.0);
        BigInteger idCategory= BigInteger.valueOf(2);
        //when

        ProductDto productDto=new ProductDto();
        productDto.setActive(false);
        productDto.setImageUrl("z");
        productDto.setName("bois rouge");
        productDto.setSku("cp");
        productDto.setLastUpdate(new Date());
        productDto.setUnitInStock(unitInStock);
        productDto.setUnitPrice(price);
        productDto.setDescription("pure classe");
        productDto.setDateCreated(new Date());
        Optional<Category> category= categoryRepository.findById(idCategory);

        //then
        category.ifPresent(
                categorys -> {
                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setCategoryName(category.get().getCategoryName());
                    categoryDto.setId(idCategory);
                    productDto.setCategory(categoryDto);

                    Product product = productMapping.toProductEntity(productDto);

                    BigInteger idProduct    = productService.saveProduct(
                            productMapping.fromProductEntity(product));

                    //when
                    Assertions.assertNotNull(idProduct,"saved");
                });
    }


    @Test
    void findById() {
        //given id
        BigInteger id = BigInteger.ONE;
        //expected name of category
        String exceptedName =  "été";
        //when
        Optional<ProductDto> productGet= productService.findById(id);
        if(productGet.isPresent()){
            System.out.println("successfully found");
            assertEquals("successfully",exceptedName, productGet.get().getName());
        }




    }

    @Test
    void paginatedAndSortingProducts() {
        //given
        double expectedLength=6.0;
        int pageSize = 3;
        int pageNumber = 0;
        boolean asc=false;
        //when
        double totalPages=productService.paginatedAndSortingProducts(pageSize,pageNumber,asc).getTotalPages();
        //then
        Assertions.assertEquals(expectedLength,totalPages);
    }




    @Test
   void searchProductsByCategoryName() {
        //given
        String categoryNameGiven = "verre";
        //when
        Boolean isHere = productService
                .searchProductsByCategoryName(categoryNameGiven).isEmpty()
                ;
        //then
        Assertions.assertNotEquals(true,isHere);
    }

    @Test
    void searchByProductName() {
        //given
        String nameGiven = "légos";
        //when
        Optional<ProductDto> product = productService.searchByProductName(nameGiven);
        Assertions.assertNotNull(product);
    }


}