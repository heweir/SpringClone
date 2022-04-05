package demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {
    //姓名
    public String name();
    //年龄
    public int age() default 19;
    //性别
    public String gender() default "男";
    //开发语言
    public String[] language();

}
