package com.he.controller;

import com.he.controller.frontend.MainPageController;
import com.he.controller.superadmin.HeadLineOperationController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//分发请求属于内存操作，对于系统来说损耗很少，解析请求匹配controller因为走的hashmap所以寻址复杂度是O(1)，损耗也很低
//“/”表示该servlet要拦截所有的请求
@WebServlet("/")
 public class DispatcherSevlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("request path is:" + req.getServletPath());
        System.out.println("request method is:"+ req.getMethod());
        if(req.getServletPath() == "/frontend/getmainpageinfo" && req.getMethod() == "GET"){
            new MainPageController().getMainPageInfo(req, resp);
        }else if(req.getServletPath() =="/supermin/addheadline" && req.getMethod() =="POST"){
            new HeadLineOperationController().addHeadLine(req,resp);
        }
    }
}
