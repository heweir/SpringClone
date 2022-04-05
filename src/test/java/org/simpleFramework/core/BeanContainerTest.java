package org.simpleFramework.core;

import com.he.controller.DispatcherSevlet;
import com.he.controller.frontend.MainPageController;
import com.he.service.solo.HeadLineService;
import com.he.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleFramework.core.annotation.BeanContainer;
import org.simpleFramework.core.annotation.Controller;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
    private static BeanContainer beanContainer;

    //有此注释，UT框架执行此用例之前，会执行一次init()
    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer:loadBeansTest")
    @Test
    @Order(1)
    public void loadBeansTest() throws InstantiationException, IllegalAccessException {
        Assertions.assertEquals(false,beanContainer.isLoaded());
        beanContainer.loadBeans("com.imooc");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }

    @DisplayName("根据类获取其实例：getBeanTest")
    @Order(2)
    @Test
    public void getBeanTest(){
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true,controller instanceof  MainPageController);

        DispatcherSevlet dispatcherSevlet = (DispatcherSevlet)beanContainer.getBean(DispatcherSevlet.class);
        Assertions.assertEquals(null,dispatcherSevlet);
    }

    @DisplayName("获取注解标记过的实例:getClassesByAnnotationTest")
    @Order(3)
    @Test
    public void getClassesByAnnotationTest(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(3,beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类:getClassesBySuperTest")
    @Order(4)
    @Test
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(true,beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}
