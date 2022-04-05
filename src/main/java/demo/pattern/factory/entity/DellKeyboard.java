package demo.pattern.factory.entity;

public class DellKeyboard implements Keyboard{
    @Override
    public void sayHello() {
        System.out.println("我是戴尔键盘");
    }
}
