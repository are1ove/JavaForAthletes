import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FearException, IOException {

        StoryWinnieAndPiglet storyWinnieAndPiglet = new StoryWinnieAndPiglet();
        StoryGrandFather storyGrandFather = new StoryGrandFather();
        LoveStory loveStory = new LoveStory();
        StoryBeasts storyBeasts = new StoryBeasts();


        storyWinnieAndPiglet.telling();
        storyGrandFather.interestingstory();
        storyWinnieAndPiglet.stopping();
        storyWinnieAndPiglet.going();
        System.out.println("*Введите путь к json файлу*");
        storyBeasts.becoming();
        storyWinnieAndPiglet.wanting();
        storyGrandFather.settingposition();

        Meeting meeting = new Meeting(Arrays.asList(loveStory.winnieThePooh, loveStory.christopherRobin));
        meeting.startEvent();
        loveStory.loving();
        storyWinnieAndPiglet.suddenlystop();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*Введите команду*");
        while (true) {
            String line = scanner.nextLine();
            String[] data = line.split(" ");
            String text;
            switch (data[0]) {
                case "insert":
                    text = line.substring(data[0].length() + 1);
                    String[] arraytext = text.split(" ");
                    for (int i = 0; i < arraytext.length; i++) {
                        if (arraytext[i].contains("Страшный зверь")) {
                            storyBeasts.beasts.put(arraytext[0], new ScaryBeast(arraytext[i]));
                        } else {
                            storyBeasts.beasts.put(arraytext[0], new UnknownBeast(arraytext[i]));
                        }
                    }
                case "remove_greater":
                    text = line.substring(data[0].length() + 1);
                    int size = storyBeasts.beasts.size();
                    for (int i = Integer.parseInt(text); i <= size; i++) {
                        storyBeasts.beasts.remove("Зверь" + i);
                    }
                case "show":
                    //int size = storyBeasts.beasts.size();
                    for (int i = 1; i <= storyBeasts.beasts.size(); i++) {
                        System.out.println(storyBeasts.beasts.get("Зверь" + i));
                    }
                default:
                    System.out.println(" ");
            }
        /*try {
            storyBeasts.steps();
        } catch (FearException e) {
            storyBeasts.countbeasts=4;
            throw e;
        }
        */
        }
    }
}


