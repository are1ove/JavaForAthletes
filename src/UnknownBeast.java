public class UnknownBeast extends Beasts {

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
