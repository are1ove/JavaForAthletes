package src;
import java.io.*;
import java.net.*;

public class Server2 {

    public static void main(String args[]) throws Exception {
        CommandTraker ct = new CommandTraker();
        int firsttime = 1;
        String clientSentence;
        String capitalizedSentence = "";
        ServerSocket welcomeSocket = new ServerSocket(3248);
        Socket connectionSocket = welcomeSocket.accept();
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        while (true) {
            clientSentence = inFromClient.readLine();
            System.out.println(clientSentence);
            if (clientSentence.equals("Exit")) {
                break;
            }
            //System.out.println(clientSentence);
            if (clientSentence.equals("set")) {
                outToClient.writeBytes("connection is ");
                System.out.println("running here");
                //welcomeSocket.close();
                //outToClient.writeBytes(capitalizedSentence);
            }

            capitalizedSentence = ct.Track(clientSentence) + "\n";
            System.out.println(capitalizedSentence);
            //if(!clientSentence.equals("quit"))
            outToClient.writeChars(capitalizedSentence);
            System.out.println("passed");
            //outToClient.writeBytes("enter the message or command: ");
        }
            welcomeSocket.close();
            System.out.println("connection terminated");
        }
    }
