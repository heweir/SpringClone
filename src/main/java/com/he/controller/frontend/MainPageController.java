package com.he.controller.frontend;

import com.he.entity.dto.MainPageInfoDTO;
import com.he.entity.dto.Result;
import com.he.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleFramework.core.annotation.Controller;
import org.simpleFramework.core.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//controller只调用service层的方法（curd）
@Controller
@Getter
public class MainPageController {
    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;
    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
