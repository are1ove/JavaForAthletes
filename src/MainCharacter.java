public abstract class MainCharacter implements NameCharacter {
    private String name;

    public MainCharacter(String name) {
        this.name = name;
    }


    abstract String move();

    abstract void meet();

    static String character;

    public String getName() {
        return name;
    }
}
