package com.locrock.sell.service;

import com.locrock.sell.dataObject.ProductCategory;

import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
public interface CategoryService
{
     ProductCategory findOne(Integer categoryId);

     List<ProductCategory> findAll();

     List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

    ProductCategory save(ProductCategory productCategory);

}
