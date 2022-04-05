package demo.pattern.factory.method;

import demo.pattern.factory.entity.Mouse;

//定义一个用于创建对象的接口，让子类决定实例化哪一个类
public class Demo {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new DellMouseImpl();
        Mouse mouse = mouseFactory.creatMouse();
        mouse.sayHi();
    }
}
