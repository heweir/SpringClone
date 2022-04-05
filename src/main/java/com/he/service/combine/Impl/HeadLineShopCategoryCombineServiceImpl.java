package com.he.service.combine.Impl;

import com.he.entity.HeadLine;
import com.he.entity.ShopCategory;
import com.he.entity.dto.MainPageInfoDTO;
import com.he.entity.dto.Result;
import com.he.service.combine.HeadLineShopCategoryCombineService;
import com.he.service.solo.HeadLineService;
import com.he.service.solo.ShopCategoryService;
import org.simpleFramework.core.annotation.Service;
import org.simpleFramework.core.inject.annotation.Autowired;

import java.util.List;

//头条，店铺信息合并操作
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1、获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition,1,4);
        //2、获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition,1,100);
        //3、合并两者返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(headLineResult,shopCategoryResult);
        return result;
    }
    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
