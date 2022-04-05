package com.he.entity.dto;

import com.he.entity.HeadLine;
import com.he.entity.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine>headLineList;
    private List<ShopCategory> shopCategoryList;
}
