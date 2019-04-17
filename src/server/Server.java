package src.server;

import src.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 *
 * @author ilya
 */
public class Server {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        
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
                    StoryWinnieAndPiglet storyWinnieAndPiglet = new StoryWinnieAndPiglet();
                    StoryGrandFather storyGrandFather = new StoryGrandFather();
                    LoveStory loveStory = new LoveStory();
                    StoryBeasts storyBeasts = new StoryBeasts();
                    
                    storyWinnieAndPiglet.telling();
                    storyGrandFather.interestingstory();
                    storyWinnieAndPiglet.stopping();
                    storyWinnieAndPiglet.going();
                    storyBeasts.load();
                    storyBeasts.becoming();
                    storyWinnieAndPiglet.wanting();
                    storyGrandFather.settingposition();
                    
                    Meeting meeting = new Meeting(Arrays.asList(loveStory.winnieThePooh, loveStory.christopherRobin));
                    meeting.startEvent();
                    loveStory.loving();
                    storyWinnieAndPiglet.suddenlystop();
                    System.out.println("*Введите команду*");
                    while (true) {
                        String line = reader.readLine();
                        String[] data = line.split(" ");
                        String text;
                        switch (data[0]) {
                            case "insert":
                                if (line.equals("insert")) {
                                System.err.println("Вы не ввели, кого вы хотите добавить в коллекцию");
                                } else {
                                    text = line.substring(data[0].length() + 1);
                                    storyBeasts.insert(text);
                                }
                                break;

                            case "remove_greater":
                                if (line.equals("remove_greater")) {
                                    System.err.println("Вы не ввели, с какого элемента вы хотите удалить элементы из коллекции");
                                } else {
                                    text = line.substring(data[0].length() + 1);
                                    storyBeasts.remove_greater(text);
                                }
                                break;

                            case "show":
                                storyBeasts.show();
                                break;

                            case "save":
                                storyBeasts.save();
                                break;

                            case "info":
                                storyBeasts.info();
                                break;

                            case "remove":
                                if (line.equals("remove")) {
                                    System.err.println("Вы не ввели, какой элемент вы хотите удалить");
                                } else {
                                    text = line.substring(data[0].length() + 1);
                                    storyBeasts.remove(text);
                                }
                                break;

                            case "load":
                                storyBeasts.load();
                                break;
                            default:
                                storyBeasts.printHelp();
                        }
                        storyBeasts.steps();
                        
                        //String response = (int)(Math.random()*30-10)+ "";
                        //System.out.println("Responce:" + response);
                        //writer.write(response);
                        //writer.newLine();
                        //writer.flush();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
