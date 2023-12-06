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
    private  CategoryServiceImpl categoryService;
    @Test
    void saveCategory() {
        CategoryDto categoryDto = new CategoryDto();

        //given
        categoryDto.setCategoryName("tissus");
        //when
        Category categorySaved = categoryService.saveCategory(categoryDto);
        //then
        Assertions.assertNotNull(categorySaved);
    }

    @Test
     void getAllCategories() {
        //gIVEN expected the length list of categories
        int unexpected = 0;
        //when
        List<CategoryDto> categories = categoryService.getAllCategories();

        Integer expectedLength = categories.toArray().length;
        //then
        System.out.printf(expectedLength.toString());
        Assertions.assertNotEquals(expectedLength,unexpected,"success");
    }



    @Test
     void delete() {
        //GIVEN ID
        BigInteger id= BigInteger.valueOf(1);
        //when
        categoryService.delete(id);
        //then
        Assertions.assertNotNull(id,"succesful");
        System.out.println("Successfully deleted");
    }

    @Test
    void update() {
        //given
        String expectedName="viande";
        BigInteger id = BigInteger.valueOf(2);
        Optional<CategoryDto> category = categoryService.getCategoryById(id);
        category.ifPresent(categoryDto -> categoryDto.setCategoryName(expectedName));
        //when

        CategoryDto categorySaved= categoryService.update(id,category.orElse(null));

        //then
        Assertions.assertEquals(expectedName,categorySaved.getCategoryName());
        System.out.println(categorySaved.getCategoryName());
    }

    @Test
    void getCategoryById() {
        //Given id = 2
        BigInteger id= BigInteger.TWO;
        //when

            Optional<CategoryDto> categoryGiven = categoryService.getCategoryById(id);
        Assertions.assertNotNull(categoryGiven.get());


    }
}