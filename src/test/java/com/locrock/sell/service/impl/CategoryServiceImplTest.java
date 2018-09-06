package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.ProductCategory;
import com.locrock.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith (SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest
{
    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne ()
    {
        ProductCategory one = categoryService.findOne (1);

        Assert.assertEquals (new Integer ( 1) ,one.getCategoryId () );
    }

    @Test
    public void findAll ()
    {
        List<ProductCategory> productCategories = categoryService.findAll ();

        Assert.assertNotEquals (0,productCategories.size ());
    }

    @Test
    public void findByCategoryTypeIn ()
    {
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn (Arrays.asList (1, 2));

        Assert.assertNotEquals (0,byCategoryTypeIn.size ());
    }

    @Test
    public void save ()
    {
        ProductCategory category=new ProductCategory ();
        category.setCategoryName ("零食专区");
        category.setCategoryType (2);

        ProductCategory save = categoryService.save (category);

        Assert.assertNotNull (save);
    }
}