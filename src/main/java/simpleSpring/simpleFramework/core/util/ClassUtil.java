package org.simpleFramework.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
//传入包名，通过的类加载器和反射来获取包下所有类对象，即获取指定范围内的Class对象
public class ClassUtil {
    public static final String FILE_PROTOCOL = "file";

    /*
        获取包下类的集合
        @parampackageName包名
        @return类集合
        */
    public static Set<Class<?>> extractPackageClass(String packageName){
        //1.获取到类的加载器
        ClassLoader classLoader = getClassLoad();

        //2.通过类加载器获取到加载的资源
        URL url = classLoader.getResource( packageName.replace(".","/"));
        if(url == null){
            log.warn("unable to retrieve anything from package:"+packageName);
            return null;
        }

        //3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        //过滤出文件类型的资源
        if(url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet,packageDirectory,packageName);
        }
        //TODO此处可以加入针对其他类型资源的处理
        return classSet;
    }

    /**
     * 递归获取目标package里面的所有的class文件（包含子package里的clas文件）
     * @param emptyClassSet 装在目标类的集合
     * @param fileSource 文件或目录
     * @param packageName 包名
     * @return 类集合
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        //如果递归到文件则终止
        if(!fileSource.isDirectory())
            return;
        //如果时一个文件夹，则调用其listFiles方法获取文件夹下的文件或者文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            //过滤文件夹和class文件
            @Override
            public boolean accept(File file) {
                if(file.isDirectory()){
                    return true;
                }
                else {
                    //获取文件的绝对值路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if(absoluteFilePath.endsWith(".class")){
                        //如果是class文件，则直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }
            //根据class文件的绝对值路径，获取并生成class对象，并放入classSet中

            private void addToClassSet(String absoluteFilePath){
                //1. 从class文件的绝对值路径里提取出包含了package的类名
                //如 users/baidu/imooc/springfrmework/sampleSpring/com/immoc/entity/dto/MainPageInfoDTO
                //转化成com.immoc.entity.dto.MainPageInfoDTO
                absoluteFilePath = absoluteFilePath.replace(File.separator,".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));

                //2.通过反射机制，获取对应的Class对象并加入到classSet中
                Class targetClass = loadClass(className);
                emptyClassSet.add(targetClass);

            }
        });

        if(files != null){
            for(File f:files){
                //递归调用
                extractClassFile(emptyClassSet,f,packageName);
            }
        }
    }

    /**
     * 由类的全限定名，反射获取Class对象
     * @param className class全名 = package + 类名
     * @return Class
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:",e);
            throw new RuntimeException(e);
        }
    }

    /**
     *获取classLoader
     * @return 当前ClassLoader
     */
    public static ClassLoader getClassLoad(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     *
     * @param clazz Class
     * @param accessible 是否支持创建出私有class对象的实例
     * @param <T> class的类型
     * @return 类的实例化
     */
    public static <T> T newInstance(Class<?> clazz,boolean accessible){
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置类的属性值
     * @param field 成员变量
     * @param targetBean 类实例,bean
     * @param value 成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field,Object targetBean,Object value,boolean accessible){
        field.setAccessible(accessible);
        //给target实例的field成员变量设value值
        try {
            field.set(targetBean,value);
        } catch (IllegalAccessException e) {
            log.error("setField error",e);
            throw new RuntimeException(e);
        }
    }

}
