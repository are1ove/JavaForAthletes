package example1;

import src.Func;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable {
    final Socket socket;

    public void run() {
        try {

            Func theFunt = new Func();

            System.out.println("Handle client connection...");
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

            boolean keepDoing = true;
            while (keepDoing) {
                Command cmd = (Command) inFromClient.readObject();

                System.out.format("Get command from client: \"%s\"\n", cmd.name);

                Status status;

                switch (cmd.name) {
                    case "bye":
                        keepDoing = false;

                        status = new Status(0, "bye");
                        break;
                    case "printHelp":
                        status = new Status(0, theFunt.printHelp());
                        break;
                    case "insert":
                        List<String> args = (List<String>)cmd.data;
                        if (args.size() == 2) {
                            status = new Status(0, theFunt.insert(args.get(0) + " " + args.get(1)));
                        } else {
                            status = new Status(1, "Failure: commant \"insert\" requires two arguments!\n");
                        }
                    default:
                        status = new Status(0, "Success\nReally!\n");
                }

                outToClient.writeObject(status);
                outToClient.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Server(Socket s) {
        socket = s;
    }

    public static void main(String... args) throws IOException {
        final ServerSocket welcomeSocket = new ServerSocket(3248);
        long clientCount = 0;
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            System.out.println("Accpted connection....");
            Server server = new Server(connectionSocket);

            Thread t = new Thread(server, "Clinet Connection " + clientCount++);
            t.start();
        }
    }
}
