package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StoryBeasts {
    int countbeasts;
    public final Date CREATE_DATE;
    private static String traces = "следы ";

    public StoryBeasts() {
        CREATE_DATE = new Date();
    }

    private class StoryTraces {
        void trace() {
            System.out.print("Потому что были " + traces);
        }

        String action() {
            return traces + "немного путались ";
        }
    }

    StoryTraces storyTraces = new StoryTraces();



    LinkedHashMap<String, Beasts> beasts = new LinkedHashMap<>();
    ArrayList<String> keys = new ArrayList<>();

    /**
     * Начало истории.
     * load: перечитать коллекцию из файла.
     *
     * @since 1.0
     */
    ArrayList<String> inpStrings = new ArrayList<>();
    public void load() {
        try {
            System.out.println("*Введите путь к json файлу*");
            Scanner scanner = new Scanner(System.in);
            FileInputStream fis = new FileInputStream(new File(scanner.nextLine()));
            InputStreamReader reader = new InputStreamReader(fis);
            int data;
            StringBuilder tempString = new StringBuilder();
            while (true) {
                data = reader.read();
                if (data == -1) break;
                char curChar = (char) data;
                switch (curChar) {
                    case '\n': {
                        inpStrings.add(tempString.toString());
                        tempString = new StringBuilder();
                        break;
                    }
                    case '{':
                        continue;
                    case '}':
                        continue;
                    case ',':
                        continue;
                    default: {
                        tempString.append(curChar);
                        break;
                    }
                }

            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Неправильный путь к файлу");
        }
    }
    public void becoming() {
            for (int i = 0; i < inpStrings.size(); i++) {
                if (inpStrings.get(i).contains("name")) {
                    String str = inpStrings.get(i).substring(inpStrings.get(i).indexOf(":") + 2, inpStrings.get(i).length() - 1);
                    inpStrings.set(i, str);
                    if (str.contains("Страшный зверь")) {
                        beasts.put("Зверь" + i, new ScaryBeast(str));
                        countbeasts+=1;
                        keys.add("Зверь" + i);
                    } else {
                        beasts.put("Зверь" + i, new UnknownBeast(str));
                        countbeasts+=1;
                        keys.add("Зверь" + i);
                    }
                }

            }

            if (Math.random() > 0.1D) {
                System.out.print("Потому что ");
                for (int i = 0; i < beasts.size()/2; i++) {
                    System.out.println(beasts.get("Зверь"+(i)));
                }
                System.out.println("Могли оказаться ");
                for (int i = beasts.size()/2; i < beasts.size(); i++) {
                    System.out.println(beasts.get("Зверь"+(i)));
                }
            } else System.out.println("Потому что боялись ");
        }


    private static class Paws {

        StoryBeasts storyBeasts = new StoryBeasts();
        public int countpaws=storyBeasts.keys.size();
        static String kit = " комплектов ";

        String was() {
            return " это были " + traces + "их" + kit + "лап";
        }

        public String sure() {
            return "можно поставить под сомнение, что";
        }
    }

    public String steps() {
        Paws paws = new Paws() {
            @Override
            public String sure() {
                return "Но совершенно несомненно ";
            }
        };
        return "Уже " + beasts.size() + " зверя!!! " + "\n" + storyTraces.action()+ "\n" + paws.sure()+ paws.was();
    }


    /**
     * show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
     *
     * @since 1.0
     */
    public void show() {
        try{
            Set set1 = beasts.entrySet();
            for (Object element : set1) {
            Map.Entry mapEntry = (Map.Entry) element;
            System.out.println(mapEntry);
        }
        }
        catch(Exception e){
            System.err.println("Неправильный ввод");
        }
    }

    /**
     * Метод save сохраняет коллекцию в файл.
     *
     * @since 1.0
     */
    public void save() {
        try{
        try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/SomeBeasts.json")) {
            Set set = beasts.entrySet();
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
    }
        catch(Exception e){
         System.err.println("Неправильный ввод");
    }
    }
    

    /**
     * Метод info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     *
     * @return 
     * @since 1.0
     */
    public String info(){
        return "Тип коллекции: " + beasts.getClass() + "\n" + "Количество элементов в коллекци: " + beasts.size() + "\n" + "Дата создания: " + CREATE_DATE + "\n";
    }
    
    /**
     * remove {String key}: удалить элемент из коллекции по его ключу.
     * Пример: remove Зверь1
     * При вводе несуществующего {String key} коллекция останется прежней.
     *
     * @param text
     * @return 
     * @since 1.0
     */
    public String remove(String text) {
        String str = null;
        try {
            if (beasts.containsKey(text)){
                beasts.remove(text);
                str = "*Элемент успешно удален*";
            } else {
                str = "*Неправильный ввод*";
            }
            try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/backup.json")) {
                Set set = beasts.entrySet();
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
        }
        catch (Exception e){
            str = "*Неправильный ввод*";
        }
        return str;
    }
    
    
    
    /**
     * Помощь в командах.
     *
     * @return 
     * @since 1.0
     */
    public String printHelp() {
        return "insert {String key} {element} - добавить новый элемент с заданным ключом" + "\n" 
                + "remove_greater {element} - удалить из коллекции все элементы, превышающие заданный"+ "\n"
                + "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" + "\n"
                + "save - сохранить коллекцию в файл" + "\n"
                + "info - вывести в стандартный поток вывода информацию о коллекции" + "\n"
                + "remove {String key} - удалить элемент из коллекции по его ключу" + "\n"
                + "load - перечитать коллекцию из файла" + "\n";
    }
    
    /**
     * remove_greater_key {String key}: удалить из коллекции все элементы, ключ которых превышает заданный.
     * Пример: remove_greater 2
     * При вводе {String key} большего чем количество элементов, коллекция не изменится.
     *
     * @since 1.0
     */
    public String remove_greater(String text) {
        String ret = null;
        try {
            int size = beasts.size();
            for (int i = Integer.parseInt(text); i < size; i++) {
                beasts.remove(keys.get(i));
            }
            ret = "*Элементы успешно удалены*";
            
            try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/backup.json")) {
                Set set = beasts.entrySet();
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
        }
        catch(Exception e){
            System.err.println("Неправильный ввод");
        }
        return ret;
    }

    /**
     * insert {String key} {element}: Метод для добавления нового элемента с заданным ключом.
     * Пример: insert Зверь1 Страшный зверь 1
     * При вводе {String key} {element} другого формата в консоль будет выведено сообщение "Неправильный ввод".
     *
     * @since 1.0
     */
    public String insert(String text) {
        String[] arraytext = text.split(" ");
        String flag = "*Неправильный ввод*";
        try { 
            if (arraytext[1].contains("Страшный")) {
                beasts.put(arraytext[0], new ScaryBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                keys.add(arraytext[0]);
                flag = "*Зверь успешно добавлен*";
            } else if (arraytext[1].contains("Неизвестный")) {
                beasts.put(arraytext[0], new UnknownBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                keys.add(arraytext[0]);
                flag = "*Зверь успешно добавлен*";
            } else {
                flag = "*Неправильный ввод*";
            }
            try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/backup.json")) {
                Set set = beasts.entrySet();
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
        }
        catch (Exception ex){
            System.err.println("Неправильный ввод");
        }
        return flag;
    }
    
}