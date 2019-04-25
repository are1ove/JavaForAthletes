package src;

import java.util.Date;

public abstract class Beasts implements NameCharacter, Comparable<Beasts> {
    private String name;
    int IntName;
    public Beasts(String name) {
        this.name = name;

    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(Beasts p){
        return this.getIntName() - p.getIntName();
        
    }
    
    public int getIntName(){
        String IntNamestr;
        IntNamestr = name.substring(name.length()-1);
        IntName = Integer.parseInt(IntNamestr);
        return IntName;
    }
   
}
