package org.simpleFramework.core.annotation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleFramework.core.util.ClassUtil;
import org.simpleFramework.core.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用枚举+饿汉方式:
 * 实现线程安全的防反射攻击的容器
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) //有一个无参构造函数
public class BeanContainer {

    //1. 配置的管理和获取
    /**
     *存放所有被配置标记的目标对象的Map
     * 用支持并发的ConcurrentHashMap，其用CAS和红黑树实现
     */
    private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap<>();
    /**
     * 加载bean的注解列表
     */

    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class,Controller.class,Service.class,Repository.class);

    /**
     * 获取Bean容器实例
     * @return BeanContainer
     */

    public static BeanContainer getInstance(){

        return ContainerHolder.HOLDER.instance;

    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        private ContainerHolder(){
            instance = new BeanContainer();
        }
    }
    /**
     * 容器是否已经加载过Bean
     */
    private boolean loaded = false;
    /**
     * 是否已加载Bean
     * @return 是否已加载
     */
    public boolean isLoaded(){
        return loaded;
    }
    /**
     * 扫描加载所有的Bean
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName) throws InstantiationException, IllegalAccessException {
        //判断容器是否被加载过
        if(isLoaded()){
            log.warn("extract nothing from packageName"+packageName);
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if(ValidationUtil.isEmpty(classSet)){
            log.warn("从包名中提取不到任何类"+packageName);
            return;
        }
        for(Class<?> clazz:classSet){
            for(Class<? extends  Annotation> annotation:BEAN_ANNOTATION){
                //如果类上面标记了定义的注解
                if(clazz.isAnnotationPresent(annotation)){
                    //将目标类本身作为键，目标类的实例作为值，放入到beanMap
                    beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
                }
            }
        }
        loaded = true;
    }
    /**
     * Bean实例数量
     * @return 返回数量
     */
    public int size(){
        return beanMap.size();
    }
    /**
     *
     * @param clazz Class对象
     * @param bean Bean实例
     * @return 原有的Bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz,Object bean){
        return beanMap.put(clazz,bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     * @param clazz Class对象
     * @return 删除的Bean实例 没有则返回null
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     * @return Class集合
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }

    /**
     * 获取所有的Bean集合
     * @return Bean集合
     */
    public Set<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的Class集合
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation){
        //1. 获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2. 筛选被注解标记的class对象，并以set集返回
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz : keySet){
            //类是否有相关的注解标记
            if(clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }

    /**
     * 通过接口或者父类，获取实现类或者子类的Class集合，不包括其本身
     * @param interfaceOrClass 接口Class或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass){
        //1. 获取beanMap的所有class对象
        Set<Class<?>> keySet = getClasses();
        if(ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2. 判断keySet里的元素是否是传入的接口或者类的子类，是则添加到classSet
        Set<Class<?>> classSet = new HashSet<>();
        for(Class<?> clazz : keySet){
            if(interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size()>0?classSet:null;
    }
}
