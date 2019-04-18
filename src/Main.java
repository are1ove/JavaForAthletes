import java.io.IOException;
import java.util.*;

/**
 * @author ilya aka Chaps & Valeriy aka Stakan
 */
public class Main {

    /**
     * @param args
     * @throws FearException
     * @throws IOException
     */
    public static void main(String[] args) {

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("*Введите команду*");
        while (true) {
            String line = scanner.nextLine();
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
                    printHelp();
            }

            storyBeasts.steps();


        }

    }

    /**
     * Помощь в командах.
     *
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




