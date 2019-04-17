package src.server;

import src.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author ilya
 */
public class Server {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        StoryBeasts storyBeasts = new StoryBeasts();
        try (ServerSocket server = new ServerSocket(9000))
        {
            System.out.println("Сервер начал работу...");
            
            while (true)
                try(
                    Socket socket = server.accept();
                    BufferedWriter writer = 
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream()));
                    BufferedReader reader = 
                            new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));
                ){
                    String request = reader.readLine();
                    System.out.println("Request: " + request);
                    String response = (int)(Math.random()*30-10)+ "";
                    System.out.println("Responce:" + response);
                    writer.write(response);
                    writer.newLine();
                    writer.flush();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
