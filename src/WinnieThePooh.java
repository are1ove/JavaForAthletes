public class WinnieThePooh extends MainCharacter implements NameCharacter {
    public static String stop = "остановился";
    public static String think = "думал";


    public WinnieThePooh(String name) {
        super(name);

    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    String move() {
        System.out.println(stop);
        return null;
    }


    @Override
    void meet() {

    }

    public static void intellection() {
        think = "думал";


    }

    @Override
    public String toString() {
        return move();
    }

}
