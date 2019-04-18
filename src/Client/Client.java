package src.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * @author ilya
 */
public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
            
            try (Socket socket = new Socket("127.0.0.1", 9000);
                BufferedWriter writer = 
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()));
                BufferedReader reader = 
                        new BufferedReader(
                                new InputStreamReader(System.in));
                
                DataInputStream in = new DataInputStream(socket.getInputStream());
                ObjectInputStream ois = new ObjectInputStream(in);
                    
                ){
                    System.out.println("Соединение с сервером установлено...");
                       
                     while(!socket.isOutputShutdown()){
                        if(reader.ready()){
                            String request = reader.readLine();
                            writer.write(request);
                            writer.newLine();
                            writer.flush();
                            Object ob = ois.readObject();
                            System.out.print(ob);
                            if(request.equalsIgnoreCase("finish")){
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
