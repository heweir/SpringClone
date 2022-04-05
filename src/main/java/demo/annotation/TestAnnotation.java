package demo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注解可以用在方法上
@Target(ElementType.METHOD)
//source表示该注解只会在对应类的源代码中保留，并不会在class文件中保留
@Retention(RetentionPolicy.SOURCE)
public @interface TestAnnotation {

}
