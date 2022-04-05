package demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationParser {

    //解析类的注解
    public static void parseClassAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.ImoocCourse");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
//          CourseInfoAnnotation courseInfoAnnotation1 = (CourseInfoAnnotation)clazz.getAnnotation(CourseInfoAnnotation.class);
            System.out.println("课程名：" + courseInfoAnnotation.courseName() + "\n" +
                    "课程标签：" + courseInfoAnnotation.courseTag() + "\n" +
                    "课程简介：" + courseInfoAnnotation.courseProfile() + "\n" +
                    "课程序号:" + courseInfoAnnotation.courseIndex());
        }
    }

    //解析成员变量的注解
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.ImoocCourse");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("---");
            //判断成员变量中是否有指定注解类型的注解
            boolean hasAnnotation = field.isAnnotationPresent(PersonInfoAnnotation.class);
            if (hasAnnotation) {
                PersonInfoAnnotation personInfoAnnotation = field.getAnnotation(PersonInfoAnnotation.class);
                System.out.println("姓名：" + personInfoAnnotation.name() + "年龄：" + personInfoAnnotation.age() + "性别：" + personInfoAnnotation.gender() + "\n");
                for (String language : personInfoAnnotation.language()) {
                    System.out.println("语言：" + language);
                }
            }
        }
    }
    //解析方法注解
    public static void parseMethodAnnotation () throws ClassNotFoundException {
        Class clacc = Class.forName("demo.annotation.ImoocCourse");
        Method[] methods = clacc.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("---");
            boolean hasAnnotation = method.isAnnotationPresent(CourseInfoAnnotation.class);
            if (hasAnnotation) {
//                Annotation[] annotations = method.getAnnotations();
//                for (Annotation annotation : annotations) {
                    CourseInfoAnnotation courseInfoAnnotation = method.getAnnotation(CourseInfoAnnotation.class);
                    System.out.println("课程名：" + courseInfoAnnotation.courseName() + "\n" +
                            "课程标签：" + courseInfoAnnotation.courseTag() + "\n" +
                            "课程简介：" + courseInfoAnnotation.courseProfile() + "\n" +
                            "课程序号:" + courseInfoAnnotation.courseIndex());
            }
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {
        AnnotationParser.parseFieldAnnotation();
    }
}