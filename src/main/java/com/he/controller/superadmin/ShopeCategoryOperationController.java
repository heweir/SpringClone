package com.he.controller.superadmin;

import com.he.entity.HeadLine;
import com.he.entity.ShopCategory;
import com.he.entity.dto.Result;
import com.he.service.solo.ShopCategoryService;
import org.simpleFramework.core.annotation.Controller;
import org.simpleFramework.core.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ShopeCategoryOperationController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    public Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return  shopCategoryService.addShopCategory(new ShopCategory());
    }
    public Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return  shopCategoryService.removeShopCategory(1);
    }
    public Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return  shopCategoryService.modifyShopCategory(new ShopCategory());
    }
    public Result<HeadLine> queryShopCategoryById(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return  shopCategoryService.queryShopCategoryById(1);
    }
    public Result<List<ShopCategory>> queryShopCategory(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return  shopCategoryService.queryShopCategory(null,1,100);
    }
}
