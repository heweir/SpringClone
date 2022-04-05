package com.he;

import com.he.entity.HeadLine;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
//    Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    public void init(){
        System.out.println("初始化Servlet...");
    }

    @Override
    public void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("是我执行了doGet方法，我才是入口");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架";
        log.debug("name is"+name);
        req.setAttribute("name",name);
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req,resp);

        HeadLine headLine = new HeadLine();
        headLine.setLineId(1L);
        headLine.getLineId();

    }
    @Override
    public void destroy(){
        System.out.println("destroy...");
    }
}
