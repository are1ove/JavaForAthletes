package src;

public class GrandFatherUnauthorisedV implements NameCharacter, Position {
    public static String name;
    public static String there = "там";
    public static String here = "тут";
    public String position;

    public GrandFatherUnauthorisedV(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String getPosition() {
        return position;
    }

    public String was() {
        return "был";
    }

}
