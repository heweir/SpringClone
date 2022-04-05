package demo.pattern.factory.entity;

public class HpKeyboard implements Keyboard{
    @Override
    public void sayHello() {
        System.out.println("我是惠普键盘");
    }
}
