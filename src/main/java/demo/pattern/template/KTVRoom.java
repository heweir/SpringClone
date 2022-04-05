package demo.pattern.template;

/**
 * 模板方法模式案例
 */
public abstract class KTVRoom {
    public void procedure(){
        openDevice();
        orderSong();
        orderExtra();
        pay();
    }
    //模板自带方法
    private void openDevice() {
        System.out.println("打开视频和音响");
    }
    //抽象方法，子类必须实现的方法
    protected abstract void orderSong();
    //钩子，额外开销视情况选择
    private void orderExtra() {
    }
    //模板自带方法，用完必须付款
    private void pay(){
        System.out.println("支付本次消费账单");
    }
}
