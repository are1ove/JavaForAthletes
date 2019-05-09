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
                if (tokens.length == 3) {
                    return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате кого вы хотите добавить");
                }
                break;
            case "remove":
                if (tokens.length == 2) {
                    return new Command(tokens[0], Arrays.asList(tokens[1]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате какой элемент вы хотите удалить");
                }
                break;
            case "remove_greater":
                if (tokens.length == 2) {
                    return new Command(tokens[0], Arrays.asList(tokens[1]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате какие элементы вы хотите удалить");
                }
                break;
            case "import":
                if (tokens.length == 2) {
                    return new Command(tokens[0], Arrays.asList(tokens[1]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате путь к файлу который вы хотите загрузить");
                }
                break;
            case "login":
                if (tokens.length == 3) {
                    return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате логин и пароль");
                }
                break;
            case "sign_up":
                if (tokens.length == 3) {
                    return new Command(tokens[0], Arrays.asList(tokens[1], tokens[2]));
                } else {
                    System.out.println("Неправильный формат\nВведите в правильном формате логин и почту");
                }
                break;
            default:
                return new Command(tokens[0], null);
        }
        return new Command(tokens[0], Arrays.asList(tokens[0]));
    }
}
