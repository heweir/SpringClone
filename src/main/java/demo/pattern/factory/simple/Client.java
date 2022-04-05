package demo.pattern.factory.simple;

import demo.pattern.factory.entity.Mouse;

public class Client {
    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse(1);
        mouse.sayHi();
    }
}
