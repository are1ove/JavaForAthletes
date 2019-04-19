package src;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandTraker {
    Func func = new Func();

    public String Track(String line){
            String[] data = line.split(" ");
            String text;
            switch (data[0]) {

                case "insert":
                    if (line.equals("insert")) {
                        System.err.println("Вы не ввели, кого вы хотите добавить в коллекцию");
                    } else {
                        text = line.substring(data[0].length() + 1);
                        return func.insert(text);
                    }
                    break;

                case "remove_greater":
                    if (line.equals("remove_greater")) {
                        System.err.println("Вы не ввели, с какого элемента вы хотите удалить элементы из коллекции");
                    } else {
                        text = line.substring(data[0].length() + 1);
                        return func.remove_greater(text);
                    }
                    break;

                case "show":
                    return func.show();


                case "save":
                    return func.save();


                case "info":
                    return func.info();


                case "remove":
                    if (line.equals("remove")) {
                        System.err.println("Вы не ввели, какой элемент вы хотите удалить");
                    } else {
                        text = line.substring(data[0].length() + 1);
                        return func.remove(text);
                    }
                    break;

                case "load":
                    return func.load();

                default:
                    return func.printHelp();
            }
        return "no way";

    }
}

