package org.simpleFramework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleFramework.core.util.ClassUtil;

import java.util.Set;

public class ClassUtilTest {
    @DisplayName("提取目标类方法：extractPackageClassTest")//用例的名称
    @Test
    public void extractPackageClassTest(){
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("com.imooc.entity");
        System.out.println(classSet);
        Assertions.assertEquals(4,classSet.size());
    }
}
