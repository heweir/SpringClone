package demo.pattern.factory.method;

import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.Mouse;

public class DellMouseImpl implements MouseFactory{
    @Override
    public Mouse creatMouse() {
        return new DellMouse();
    }
}
