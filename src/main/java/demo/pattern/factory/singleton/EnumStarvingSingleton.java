package demo.pattern.factory.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 *  通过反射可以直接入侵类的私有构造函数，实例化类的对象；
 *  枚举类无法通过反射入侵，因此能够解决上述问题；
 *
 */
public class EnumStarvingSingleton {
    private EnumStarvingSingleton() {
    }

    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private EnumStarvingSingleton instance;
        ContainerHolder(){
            instance = new EnumStarvingSingleton();
        }
    }
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(EnumStarvingSingleton.getInstance());
        System.out.println(EnumStarvingSingleton.getInstance());

        Class clazz = EnumStarvingSingleton.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            System.out.println(constructor.newInstance());
        }


        catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
