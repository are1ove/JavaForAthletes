package client_server;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;



public class Client {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        try {
            final Socket clientSocket = new Socket("localhost", 3248);

            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());

            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

                String line = consoleReader.readLine();

                do {
                    if (line != null && !line.isEmpty()) {

                        System.out.format("Execute command: \"%s\"\n", line);

                        Command cmd = Command.getCommand(line);


                        System.out.println("Send command to server...");
                        writer.writeObject(cmd);
                        //writer.flush();


                        System.out.println("Handle server response");

                        Status status = (Status) inFromServer.readObject();

                        System.out.format("Status: %d, %s\n", status.errorCode, status.message);

                        if (status.message.equals("bye")) {
                            System.out.println("Disconnect from server");
                            System.exit(0);
                        }

                        System.out.println("Done\n");
                    }
                    line = consoleReader.readLine();
                    System.out.println("Next command: " + line);
                } while (!line.equals("exit")&&clientSocket.isConnected());

                System.out.println("Done");

            }

        } catch (SocketException e) {
            System.out.println("\nServer is not available\nTry to connect later");
        }
    }

}
