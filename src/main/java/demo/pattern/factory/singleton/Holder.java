package demo.pattern.factory.singleton;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**Holder模式(推荐)
 * 将延迟加载和线程安全完美结合的一种方式（无锁）,但是还是容易被反射攻击
 */
public class Holder {

        /**
         * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
         * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
         */
        private static class SingletonHolder{
            /**
             * 静态初始化器，由JVM来保证线程安全
             */
            private static  Holder instance = new Holder();
        }
        private Holder(){
        }
        public static  Holder getInstance(){
            return SingletonHolder.instance;
        }

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(Holder.getInstance());
        System.out.println(Holder.getInstance());


        Class clazz = Holder.class;
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




