package src;


import java.util.List;

public class Meeting extends Event {
    List<MainCharacter> meetingCharacters;

    public Meeting(List<MainCharacter> meetingCharacters) {

        this.meetingCharacters = meetingCharacters;
    }

    @Override
    public EventSequence startEvent() {
        for (MainCharacter character : meetingCharacters) {
            System.out.println("На встрече присутствует " + character.getName());
        }
        return (Math.random() > 0.5D) ? EventSequence.GOOD : EventSequence.BAD;
    }
}

