import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
                    if (arraytext[1].contains("Страшный")) {
                        storyBeasts.beasts.put(arraytext[0], new ScaryBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                        storyBeasts.keys.add(arraytext[0]);
                    } else if (arraytext[1].contains("Неизвестный")) {
                        storyBeasts.beasts.put(arraytext[0], new UnknownBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                        storyBeasts.keys.add(arraytext[0]);
                    } else {
                        System.err.println("Неправильный ввод");
                        break;
                    }
                    try (FileOutputStream fos = new FileOutputStream("/Users/valeriy/Documents/JavaProgramms/JavaForAthletes/backupfile.json")) {
                        Set set = storyBeasts.beasts.entrySet();
                        for (Object element : set) {
                            Map.Entry mapEntry = (Map.Entry) element;
                            final char dm = (char) 34;
                            String inptext = "{" + dm + "name" + dm + ":" + dm + mapEntry.getValue() + dm + "},\n";
                            byte[] buffer = inptext.getBytes();
                            fos.write(buffer, 0, buffer.length);
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "remove_greater":
                    text = line.substring(data[0].length() + 1);
                    int size = storyBeasts.beasts.size();
                    System.out.println(text);
                    for (int i = Integer.parseInt(text); i < size; i++) {
                        System.out.println(storyBeasts.beasts.get(storyBeasts.keys.get(i)));
                        storyBeasts.beasts.remove(storyBeasts.keys.get(i));

                    }
                    System.out.println(storyBeasts.keys.toString());
                    break;
                case "show":
                    Set set1 = storyBeasts.beasts.entrySet();
                    for (Object element : set1) {
                        Map.Entry mapEntry = (Map.Entry) element;
                        System.out.println(mapEntry.getValue());
                    }
                    break;

                case "save":
                    try (FileOutputStream fos = new FileOutputStream("/Users/valeriy/Documents/JavaProgramms/JavaForAthletes/src/SomeBeasts.json")) {
                        Set set = storyBeasts.beasts.entrySet();
                        for (Object element : set) {
                            Map.Entry mapEntry = (Map.Entry) element;
                            final char dm = (char) 34;
                            String inptext = "{" + dm + "name" + dm + ":" + dm + mapEntry.getValue() + dm + "},\n";
                            byte[] buffer = inptext.getBytes();
                            fos.write(buffer, 0, buffer.length);
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "info":
                    System.out.println("Тип коллекции: " + storyBeasts.beasts.getClass());
                    System.out.println("Количество элементов в коллекции: " + storyBeasts.beasts.size());
                    System.out.println("Дата создания: " + storyBeasts.CREATE_DATE);
                    break;
                case "remove":
                    text = line.substring(data[0].length() + 1);
                    storyBeasts.beasts.remove(text);
                    break;
                case "load":
                    storyBeasts.becoming();
                    break;
                default:
                    printHelp();
            }
            System.out.println(storyBeasts.beasts);
        /*try {
            storyBeasts.steps();
        } catch (FearException e) {
            storyBeasts.countbeasts=4;
            throw e;
        }
        */
        }

    }

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


