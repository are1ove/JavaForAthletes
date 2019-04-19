package src.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author ilya
 */
public class Client extends Thread {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {Socket socket = new Socket("localhost", 3128);
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     socket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(System.in));

             DataInputStream in = new DataInputStream(socket.getInputStream());
             ObjectInputStream ois = new ObjectInputStream(in);


            System.out.println("Соединение с сервером установлено...");

            while (!socket.isOutputShutdown()) {
                if (reader.ready()) {
                    String request = reader.readLine();
                    writer.write(request);
                    writer.newLine();

                    Object ob = ois.readObject();
                    System.out.print(ob);
                    writer.flush();
                    if (request.equalsIgnoreCase("finish")) {
                        System.out.println("Клиент закончил свою работу");
                        break;
                    }
                }
            }
            System.out.println("Closing connections & channels on clentSide - DONE.");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

