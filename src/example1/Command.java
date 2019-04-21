package example1;

import java.io.Serializable;
import java.util.Arrays;

public class Command implements Serializable {

    final String name;
    final Object data;

    public Command(String n, Object d) {
        name = n;
        data = d;
    }

    public static Command getCommand(String line) {
        String[] tokens = line.split(";");

        switch (tokens[0]) {
            case "insert":
                return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
            default:
                return new Command(tokens[0], null);
        }
    }
}
