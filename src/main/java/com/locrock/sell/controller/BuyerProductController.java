package com.locrock.sell.controller;

import com.locrock.sell.dataObject.ProductCategory;
import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.service.CategoryService;
import com.locrock.sell.service.ProductService;
import com.locrock.sell.util.ResultVOUtil;
import com.locrock.sell.vo.ProductInfoVO;
import com.locrock.sell.vo.ProductVO;
import com.locrock.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResultVO list ()
    {
        try {
            //查询类目(一次性查询)
            //数据拼装
//            for (ProductCategory category : categoryList) {
//
//                //封装第一层数据productVo
//                ProductVO productVO=new ProductVO ();
//                productVO.setCategoryName (category.getCategoryName ());
//                productVO.setCategoryType (category.getCategoryType ());
//
//                List<ProductInfoVO> infoVOList=new ArrayList<> ();
//
//                for (ProductInfo info : productInfoList) {
//                    if (info.getCategoryType ().equals (category.getCategoryType ())){
//                        ProductInfoVO vo=new ProductInfoVO ();
//                        vo.setProductPrice (info.getProductPrice ());
//                        vo.setProductName (info.getProductName ());
//                        vo.setProductId (info.getProductId ());
//                        vo.setProductIcon (info.getProductIcon ());
//                        vo.setProductDescription (info.getProductDescription ());
//                        infoVOList.add (vo);
//                    }
//                }
//                productVO.setProductInfoVOList (infoVOList);
//
//                dataList.add (productVO);
//            }
            //查询所有的上架商品
            //获取所有上架商品
            List<ProductInfo> productInfoList = productService.findUpAll ();
            //获取类目集合
            List<Integer> categoryTypeList=productInfoList.stream ().map (ProductInfo::getCategoryType).collect(Collectors.toList());
            //获取分类集合
            List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn (categoryTypeList);
            //数据拼装

            List<ProductVO> voList=new ArrayList<> ();

            for (ProductCategory category : productCategories) {
                ProductVO productVO=new ProductVO ();

                productVO.setCategoryType (category.getCategoryType ());
                productVO.setCategoryName (category.getCategoryName ());

                List<ProductInfoVO> infoVOList=new ArrayList<> ();

                for (ProductInfo productInfo : productInfoList) {
                    if (productInfo.getCategoryType ().equals (category.getCategoryType ())){
                        ProductInfoVO vo=new ProductInfoVO ();
                        //数据拷贝
                        BeanUtils.copyProperties (productInfo,vo);

                        infoVOList.add (vo);
                    }
                }
                productVO.setProductInfoVOList (infoVOList);

                voList.add (productVO);
            }

            return ResultVOUtil.success (voList);
        } catch (Exception e) {
            return ResultVOUtil.fail (1,"失败");
        }
    }
}
