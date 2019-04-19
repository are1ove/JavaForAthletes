package src;

import java.io.*;
import java.net.*;

class Client2 {
    public static void main(String args[]) throws Exception {
        String sentence;
        String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 3248);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Client is running");
        while(true) {
            sentence = inFromUser.readLine();
            System.out.println(sentence);
            if (sentence.equals("Stop")){
                break;
            }
            outToServer.writeBytes(sentence + "\n");
            char[] buf = new char[2048];
            modifiedSentence = inFromServer.readLine();
            modifiedSentence = modifiedSentence.toString();
            System.out.println("FROM SERVER:" + modifiedSentence);
        }
        clientSocket.close();
    }
}
