package demo.pattern.template;

public class RooomForChineseSinger extends KTVRoom{
    @Override
    protected void orderSong() {
        System.out.println("点首中文歌");
    }
    protected void orderExtra(){
        System.out.println("东西便宜，一样来一份");
    }
}
