package demo.pattern.template;

public class RoomForAmericanSinger extends KTVRoom{
    @Override
    protected void orderSong() {
        System.out.println("player an English song");
    }
}
