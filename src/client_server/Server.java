package client_server;

import src.Func;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements Runnable {
    final Socket socket;

    @Override
    public void run() {
        try {

            Func theFunc = new Func();
            Boolean bln = false;

            System.out.println("Handle client connection...");
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

            boolean keepDoing = true;
            while (keepDoing) {
                try {
                    Command cmd = (Command) inFromClient.readObject();


                    System.out.format("Get command from client: \"%s\"\n", cmd.name);

                    Status status;

                    if (bln == false){
                        switch (cmd.name) {
                            case "bye":
                                keepDoing = false;
                                status = new Status(0, "bye");
                                break;
                            case "printHelp":
                                status = new Status(0, theFunc.printHelp());
                                break;
                            case "login":
                                List<String> log_args = (List<String>) cmd.data;
                                if (log_args.size() == 2) {
                                    status = new Status(0, theFunc.login(log_args.get(0)+' '+ log_args.get(1)));
                                    if (status.getMessage() == "*Вход прошел успешно*"){
                                        bln = true;
                                    }
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"login\" requires two arguments!\n");
                                    break;
                                }
                            case "sign_up":
                                List<String> sign_args = (List<String>) cmd.data;
                                if (sign_args.size() == 2) {
                                    status = new Status(0, theFunc.sign_up(sign_args.get(0)+' '+ sign_args.get(1)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"sign_up\" requires two arguments!\n");
                                    break;
                                }
                            default:
                                status = new Status(0, "Success\nReally!\n");

                        }
                    }
                    else {
                        switch (cmd.name) {
                            case "bye":
                                keepDoing = false;
                                status = new Status(0, "bye");
                                break;
                            case "printHelp":
                                status = new Status(0, theFunc.printHelp());
                                break;
                            case "insert":
                                List<String> args = (List<String>) cmd.data;
                                if (args.size() == 2) {
                                    status = new Status(0, theFunc.insert(args.get(0) + ' ' + args.get(1)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"insert\" requires two arguments!\n");
                                    break;
                                }
                            case "login":
                                List<String> log_args = (List<String>) cmd.data;
                                if (log_args.size() == 2) {
                                    status = new Status(0, theFunc.login(log_args.get(0) + ' ' + log_args.get(1)));
                                    if (status.getMessage() == "*Вход прошел успешно*") {
                                        bln = true;
                                    }
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"login\" requires two arguments!\n");
                                    break;
                                }
                            case "sign_up":
                                List<String> sign_args = (List<String>) cmd.data;
                                if (sign_args.size() == 2) {
                                    status = new Status(0, theFunc.sign_up(sign_args.get(0) + ' ' + sign_args.get(1)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"sign_up\" requires two arguments!\n");
                                    break;
                                }
                            case "import":
                                List<String> argsss = (List<String>) cmd.data;
                                if (argsss.size() == 1) {
                                    status = new Status(0, theFunc.importt(argsss.get(0)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"import\" requires one arguments!\n");
                                    break;
                                }
                            case "info":
                                status = new Status(0, theFunc.info());
                                break;
                            case "remove":
                                List<String> arg = (List<String>) cmd.data;
                                if (arg.size() == 1) {
                                    status = new Status(0, theFunc.remove(arg.get(0)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"remove\" requires one argument!\n");
                                    break;
                                }
                            case "remove_greater":
                                List<String> argstr = (List<String>) cmd.data;
                                if (argstr.size() == 1) {
                                    status = new Status(0, theFunc.remove_greater(argstr.get(0)));
                                    break;
                                } else {
                                    status = new Status(1, "Failure: command \"remove_greater\" requires one argument!\n");
                                    break;
                                }
                            case "show":
                                status = new Status(0, theFunc.show());
                                break;
                            case "save":
                                status = new Status(0, theFunc.save());
                                break;
                            default:
                                status = new Status(0, "Success\nReally!\n");
                        }
                    }

                    outToClient.writeObject(status);
                    outToClient.flush();
                } catch (EOFException e) {
                    keepDoing = false;
                    System.out.println("Client disconnected");
                }
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

            System.out.println("Accepted connection....");
            Server server = new Server(connectionSocket);

            Thread t = new Thread(server, "Client Connection " + clientCount++);
            t.start();
        }
    }
}
