package org.simpleFramework.core.inject.annotation;

import lombok.extern.slf4j.Slf4j;
import org.simpleFramework.core.annotation.BeanContainer;
import org.simpleFramework.core.util.ClassUtil;
import org.simpleFramework.core.util.ValidationUtil;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * 提供依赖注入的服务,提供成员变量的创建
 */
@Slf4j
public class DependencyInjector {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;
    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }
    /**
     * 执行IOC
     */
    public void doIoc(){
        //1、遍历Bean容器中所有class对象
        if(ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("empty classset in BeanContainer");
            return;
        }
        for(Class<?> clazz:beanContainer.getClasses()){
            //2、遍历class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if(ValidationUtil.isEmpty(fields)){
                continue;
            }
            for(Field field:fields){
             //3、找出被Autowired标记的成员变量
                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autoWiredValue = autowired.value();
                    //4、获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5、通过反射将对应的成员变量注入到它所在的类的实例中
                    Object fieldValue = getFieldInstance(fieldClass,autoWiredValue);
                    if(fieldValue == null){
                        throw new RuntimeException("无法从容器里获取到fieldClass相关的类型,target fieldClass is:"+
                                fieldClass.getName()+autoWiredValue);
                    }
                    else{
                        //6、通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,targetBean,fieldValue,true);
                    }
                }
            }
        }
    }

    /**
     *根据Class在beanContainer里获取其实例或者实现类
     * @param fieldClass 成员变量类类型
     * @param autowiredValue 如果成员变量是接口，指定的实现类
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass,String autowiredValue) {
        //检查成员变量的类实例是否被容器生产管理过，在容器中存在
        Object fieldValue = beanContainer.getBean(fieldClass);
        if(fieldValue != null){
            return fieldValue;
        }
        else{
            Class<?> implementClass = getImplementClass(fieldClass,autowiredValue);
            if(implementClass != null){
                //接口对应的实现类在容器中存在
                return beanContainer.getBean(implementClass);
            }
            else{
                return  null;
            }
        }
    }

    /**
     * 获取接口的实现类
     * @param fieldClass 成员变量Class类类型
     * @return
     */
    private Class<?> getImplementClass(Class<?> fieldClass,String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if(!ValidationUtil.isEmpty(classSet)){
            if(ValidationUtil.isEmpty(autowiredValue)){
                //用户并没有通过qualify指定是哪个实现类
                if(classSet.size() == 1){
                    return classSet.iterator().next();
                }
                else {
                    //如果多于两个是实现类，且用户未指定其中一个实现类，则抛出异常
                    throw new RuntimeException("multipe implemented classed for"+fieldClass.getName()+
                            "please set @Autowired's value to pick one");
                }
            }
            else{
                for(Class<?> clazz:classSet){
                    //判断容器中实现类或者子类的Class集合的类名和autowire的值相等
                    if(autowiredValue.equals(clazz.getSimpleName())){
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
