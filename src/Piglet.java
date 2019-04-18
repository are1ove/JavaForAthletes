
public class Piglet extends MainCharacter implements NameCharacter {
    public static String was = "был";
    public static String go = "пошли";

    public Piglet(String name) {
        super(name);
    }

    public static String say() {
        System.out.print(" " + "рассказывал" + " ");
        return "интересные истории";
    }


    @Override
    public String getName() {

        return super.getName();
    }

    @Override
    String move() {
        System.out.println(go);
        return null;
    }

    @Override
    void meet() {
    }

    @Override
    public String toString() {
        return Piglet.go;
    }
}
