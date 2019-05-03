package client_server;

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
            case "remove":
                return new Command(tokens[0], Arrays.asList(tokens[1]));
            case "remove_greater":
                return new Command(tokens[0],Arrays.asList(tokens[1]));
            case "import":
                return new Command(tokens[0],Arrays.asList(tokens[1]));
            case "login":
                return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
            case "sign_up":
                return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
            default:
                return new Command(tokens[0], null);
        }
    }
}