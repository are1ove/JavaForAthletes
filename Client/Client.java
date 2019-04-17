package Client;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author ilya
 */
public class Client {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 9000);
            BufferedWriter writer = 
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()));
                BufferedReader reader = 
                        new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()));
        ){
            System.out.println("Соединение с сервером установлено...");
            String request = "SPb";
            System.out.println("SPb: "+ request);
            writer.write(request);
            writer.newLine();
            writer.flush();
            
            String response = reader.readLine();
            System.out.println("Response: "+ response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
