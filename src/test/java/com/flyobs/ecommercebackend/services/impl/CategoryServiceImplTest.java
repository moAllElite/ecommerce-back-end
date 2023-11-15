package com.flyobs.ecommercebackend.services.impl;

import com.flyobs.ecommercebackend.dto.CategoryDto;
import com.flyobs.ecommercebackend.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;


    @Test
    void saveCategory() {
        CategoryDto categoryDto = new CategoryDto();
        //categoryDto.setId();
        //given
        categoryDto.setCategoryName("bureautique");
        //when
        Category categorySaved = categoryService
                .saveCategory(categoryDto);
        //then
        Assertions.assertNotNull(categorySaved);
    }

    @Test
    void getAllCategories() {
        //gIVEN expected the length list of categories
        int expectedLength = 3;
        //when
        List<CategoryDto> categories = categoryService.getAllCategories();
        //then
        Assertions.assertEquals(categories.toArray().length, expectedLength);
    }

    @Test
    void getCategoryById() {
        //Given id = 2
        BigInteger id= BigInteger.valueOf(2);
        //when
        CategoryDto categoryGiven = categoryService
                .getCategoryById(id)
                .orElse(null);
        //then
        Assertions.assertNotNull(categoryGiven);
    }

    @Test
    void delete() {
        //GIVEN ID
        BigInteger id= BigInteger.valueOf(52);
        //when
         categoryService.delete(id);
         //then
        Assertions.assertNotNull(id,"succesful");
        System.out.println("Successfully deleted");
    }

    @Test
    void update() {
        //given
        BigInteger id = BigInteger.TWO;
        Optional<CategoryDto> category = categoryService.getCategoryById(id);
        //category.get().setCategoryName("meuble");

        //when
        Category categorySaved= categoryService.update(category.orElseThrow());

        //then
        Assertions.assertNotNull(categorySaved);
        System.out.println(categorySaved);
    }


}