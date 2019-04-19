package src;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandTraker {
    Func func = new Func();
    public void Track(String line, Socket socket) throws IOException{
        try {
            func.load();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream oos = new ObjectOutputStream(out);
            String[] data = line.split(" ");
            String text;
            if (data[0].equals("insert")) {
                if (line.equals("insert")) {
                    oos.writeObject("*Вы не ввели, кого вы хотите добавить в коллекцию*" + "\n");
                } else {
                    text = line.substring(data[0].length() + 1);
                    oos.writeObject(func.insert(text) + "\n");
                }
            } else if (data[0].equals("remove_greater")) {
                if (line.equals("remove_greater")) {
                    oos.writeObject("*Вы не ввели, с какого элемента вы хотите удалить элементы из коллекции*" + "\n");
                } else {
                    text = line.substring(data[0].length() + 1);
                    oos.writeObject(func.remove_greater(text) + "\n");
                }
            } else if (data[0].equals("show")) {
                func.show();
            } else if (data[0].equals("save")) {
                func.save();
                oos.writeObject("*Коллекция успешно сохранена*" + "\n");
            } else if (data[0].equals("info")) {
                oos.writeObject(func.info());
                //writer.write(storyBeasts.info());
                //writer.newLine();
                //writer.flush();
            } else if (data[0].equals("remove")) {
                if (line.equals("remove")) {
                    oos.writeObject("*Вы не ввели, какой элемент вы хотите удалить*" + "\n");
                } else {
                    text = line.substring(data[0].length() + 1);
                    func.remove(text);
                    oos.writeObject(func.remove(text) + "\n");
                }
            } else if (data[0].equals("load")) {
                func.load();
            } else {
                oos.writeObject(func.printHelp());
                //storyBeasts.addMessageWithN(("Команда не найдена"));
            }
            oos.flush();
            out.flush();
        }
        catch (IOException e){
            System.out.println("Error");
        }
    }
}
