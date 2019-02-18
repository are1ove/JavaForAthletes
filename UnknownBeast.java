public class UnknownBeast implements NameCharacter {

    public static String name;

    public UnknownBeast(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
