package src;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Beasts implements NameCharacter, Comparable<Beasts> {
    private String name;
    int IntName;
    LocalDateTime create_time;
    
    public Beasts(String name) {
        this.name = name;
        this.create_time = LocalDateTime.now();
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
    public LocalDateTime getTime(){
        return create_time;
    }

}