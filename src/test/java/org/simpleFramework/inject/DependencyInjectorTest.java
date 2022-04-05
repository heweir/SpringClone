package org.simpleFramework.inject;

import com.he.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleFramework.core.annotation.BeanContainer;
import org.simpleFramework.core.inject.annotation.DependencyInjector;

public class DependencyInjectorTest {
    @DisplayName("依赖注入，执行doIoc方法")
    @Test
    public void doIocTest() throws InstantiationException, IllegalAccessException {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.imooc");
        Assertions.assertEquals(true,beanContainer.isLoaded());

        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true,mainPageController instanceof MainPageController);

        Assertions.assertEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null,mainPageController.getHeadLineShopCategoryCombineService());
    }
}
