package src;

import java.io.*;
import java.net.*;

class Client2 {
    public static void main(String args[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 3128);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true){
            sentence = inFromUser.readLine();
            if (sentence.equals("stop")){
                break;
            }
            outToServer.writeBytes(sentence + "\n");
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER:" + modifiedSentence);
        }
        clientSocket.close();
    }
}
