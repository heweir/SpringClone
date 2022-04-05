package com.he.entity.dto;

import lombok.Data;

//lombok的Data注解，自动注入getset方法，和equals等方法
@Data
public class Result<T>{
    //本次请求的状态码,200表示成功
    private int code;
    //本次请求结果的详情
    private String msg;
    //本次请求返回的结果集
    private T data;
}
