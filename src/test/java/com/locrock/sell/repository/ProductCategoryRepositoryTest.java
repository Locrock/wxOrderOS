package com.locrock.sell.repository;

import com.locrock.sell.dataObject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Test
    public void test_findAll(){

        List<ProductCategory> all = categoryRepository.findAll();

        Assert.assertNotNull (all);

        System.out.println(all);
    }

    @Test
    public void test_save(){
        ProductCategory category=new ProductCategory();
        category.setCategoryName("电玩");
        category.setCategoryType(1);

        categoryRepository.save(category);
    }
    @Test
    public void test_update(){
        ProductCategory category=new ProductCategory();
        category.setCategoryId(3);
        category.setCategoryName("男生最爱");
        category.setCategoryType(1);

        categoryRepository.save(category);
    }

    @Test
    public void findProductInList(){
        Integer[] i=new Integer[]{1,2};

        List<ProductCategory> productCategories = categoryRepository.findByCategoryTypeIn (Arrays.asList (i));

        Assert.assertNotNull (productCategories);

        System.err.println (productCategories);
    }
}