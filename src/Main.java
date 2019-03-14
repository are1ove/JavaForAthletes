import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author ilya
 */
public class Main {

    /**
     *
     * @param args
     * @throws FearException
     * @throws IOException
     */
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
                    storyBeasts.insert(text);
                    break;

                case "remove_greater":
                    text = line.substring(data[0].length() + 1);
                    storyBeasts.remove_greater(text);
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
                    text = line.substring(data[0].length() + 1);
                    storyBeasts.remove(text);
                    break;

                case "load":
                    storyBeasts.becoming();
                    break;
                default:
                    printHelp();
            }
            System.out.println(storyBeasts.beasts);
        try {
            storyBeasts.steps();
        } catch (FearException e) {
            storyBeasts.countbeasts=4;
            throw e;
        }

        }

    }
    /**
     * Помощь в командах.
     * @since 1.0
     */
    private static void printHelp() {
        System.out.println("insert {String key} {element} - добавить новый элемент с заданным ключом");
        System.out.println("remove_greater {element} - удалить из коллекции все элементы, превышающие заданный");
        System.out.println("show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("info - вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("remove {String key} - удалить элемент из коллекции по его ключу");
        System.out.println("load - перечитать коллекцию из файла");
    }
}


