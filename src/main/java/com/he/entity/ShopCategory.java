package com.he.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCategory {
    private long shopCateoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;

}
