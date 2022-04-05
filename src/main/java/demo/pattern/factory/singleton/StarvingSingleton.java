package demo.pattern.factory.singleton;

public class StarvingSingleton {
    /**
     * private:外部不能通过.访问
     * static:只初始化一次
     * final：一经初始化，不可改变
     */
    /**
     * ## 饿汉模式
     * 类被加载的时候就立即初始化并创建唯一实例，线程安全
     */
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

    private StarvingSingleton() {
    }
    public static StarvingSingleton getInstance(){
        return starvingSingleton;
    }
}
