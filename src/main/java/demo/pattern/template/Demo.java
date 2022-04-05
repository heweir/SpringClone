package demo.pattern.template;

public class Demo {
    public static void main(String[] args) {
        KTVRoom room1 = new RooomForChineseSinger();
        room1.procedure();
        KTVRoom room2 = new RoomForAmericanSinger();
        room2.procedure();
    }
}
