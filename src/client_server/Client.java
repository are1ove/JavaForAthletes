package client_server;


import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;


public class Client {
    public static void main(String... args) throws IOException, ClassNotFoundException {
        while (true) {
            try {
                final Socket clientSocket = new Socket("localhost", 3248);

                ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());


                try  {
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                    String line = consoleReader.readLine();

                    do {
                        if (line != null && !line.isEmpty()) {

                            System.out.format("Execute command: \"%s\"\n", line);

                            Command cmd = Command.getCommand(line);


                            System.out.println("Send command to server...");
                            try {
                                writer.writeObject(cmd);
                                //writer.flush();
                            } catch (SocketException e) {
                                break;
                            }

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

                    } while (!line.equals("exit"));

                    System.out.println("Done");

                }catch (IOException e){

                }
                //  } catch (SocketException e) {
                //      System.out.println("\nServer is not available\nTry to connect later");
                //  }
            } catch (ConnectException ex) {
                continue;
            }
        }

    }
}

