package src.server;

import src.*;
import java.io.*;
import static java.lang.System.out;
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
            
            while (true){
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
                    
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(out);
                    
                    String line = reader.readLine();
                    String[] data = line.split(" ");
                    String text;
                    if (data[0].equals("insert")) {
                            if (line.equals("insert")) {
                                oos.writeObject("*Вы не ввели, кого вы хотите добавить в коллекцию*"+ "\n");
                            } else {
                                text = line.substring(data[0].length() + 1);
                                oos.writeObject(storyBeasts.insert(text)+ "\n");
                            }
                    } else if (data[0].equals("remove_greater")){
                            if (line.equals("remove_greater")) {
                                oos.writeObject("*Вы не ввели, с какого элемента вы хотите удалить элементы из коллекции*"+ "\n");
                            } else {
                                text = line.substring(data[0].length() + 1);
                                oos.writeObject(storyBeasts.remove_greater(text)+"\n");
                            }
                    } else if (data[0].equals("show")){
                            storyBeasts.show();
                    } else if (data[0].equals("save")){
                            storyBeasts.save();
                            oos.writeObject("*Коллекция успешно сохранена*" + "\n");
                    } else if (data[0].equals("info")){
                            oos.writeObject(storyBeasts.info());
                            //writer.write(storyBeasts.info());
                            //writer.newLine();
                            //writer.flush();
                    } else if (data[0].equals("remove")){
                            if (line.equals("remove")) {
                                oos.writeObject("*Вы не ввели, какой элемент вы хотите удалить*"+ "\n");
                            } else {
                                text = line.substring(data[0].length() + 1);
                                storyBeasts.remove(text);
                                oos.writeObject(storyBeasts.remove(text) + "\n");
                            }
                    } else if (data[0].equals("load")){
                            storyBeasts.load();
                    } else {
                            oos.writeObject(storyBeasts.printHelp());
                            //storyBeasts.addMessageWithN(("Команда не найдена"));
                    }
                    oos.flush();
                    out.flush();
                        
                        //String response = (int)(Math.random()*30-10)+ "";
                        //writer.write(response);
                        //writer.newLine();
                        //writer.flush();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
