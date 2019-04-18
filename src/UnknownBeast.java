package src;

public class UnknownBeast extends Beasts {

    public UnknownBeast(String name){
        super(name);
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
