public abstract class Beasts implements NameCharacter {
    private String name;

    public Beasts(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
