package com.locrock.sell.service.impl;

import com.locrock.sell.dataObject.ProductCategory;
import com.locrock.sell.repository.ProductCategoryRepository;
import com.locrock.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory findOne (Integer categoryId)
    {
        return categoryRepository.findOne (categoryId);
    }

    @Override
    public List<ProductCategory> findAll ()
    {
        return categoryRepository.findAll ();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn (List<Integer> categoryList)
    {
        return categoryRepository.findByCategoryTypeIn (categoryList);
    }

    @Override
    public ProductCategory save (ProductCategory productCategory)
    {
        return categoryRepository.save (productCategory);

    }
}
