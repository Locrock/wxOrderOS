package com.locrock.sell.repository;

import com.locrock.sell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>
{

}
