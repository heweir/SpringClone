package demo.pattern.factory.singleton;


/**
 * ## 懒汉模式
 *
 * - 在被客户端首次调用的时候才创建唯一的实例
 * - 加入**双重检查锁机制**才能确保线程安全
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance() {
        //第一次检测
        if(instance == null){
            //同步
            synchronized (LazyDoubleCheckSingleton.class){
                if(instance == null){
                    //memory = allocate(); //1.分配对象内存空间
                    //instance(memory);//2.初始化对象
                    //instance = memory; //3. 设置instance指向刚分配的内存地址，此时instance ！= null
                    //volatile保证指令不会重排序，按123执行
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
