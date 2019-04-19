package src;

import java.io.*;
import java.net.*;

class Server2 extends Thread
{
    Socket s;
    int num;

    public static void main(String args[])
    {

        try
        {
            int i = 0;
            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("server is started");

            // слушаем порт
            while(true)
            {
                new Server2(i, server.accept());
                i++;
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public Server2(int num, Socket s)
    {
        // копируем данные
        this.num = num;
        this.s = s;

        // и запускаем новый вычислительный поток (см. ф-ю run())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        Func func = new Func();
        try
        {
            CommandTraker ctr = new CommandTraker();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            byte buf[] = new byte[64*1024];
            int r = is.read(buf);

            String line = new String(buf, 0, r);
            func.load();
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
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
            // добавляем данные об адресе сокета:
            line = ""+num+": "+"\n"+data;

            // выводим данные:
            os.write(line.getBytes());

            // завершаем соединение
            s.close();
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}
