package demo.pattern.factory.method;

import demo.pattern.factory.entity.HpMouse;
import demo.pattern.factory.entity.Mouse;

public class HpMouseImpl implements MouseFactory{
    @Override
    public Mouse creatMouse() {
        return new HpMouse();
    }
}
