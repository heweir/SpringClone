package com.he.service.solo.impl;

import com.he.entity.HeadLine;
import com.he.entity.ShopCategory;
import com.he.entity.dto.Result;
import com.he.service.solo.ShopCategoryService;
import org.simpleFramework.core.annotation.Service;

import java.util.List;

//店铺类别信息操作：查询，修改等
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<HeadLine> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
