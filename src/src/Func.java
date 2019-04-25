package src;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.ScaryBeast;
import src.ScaryBeast;
import src.StoryBeasts;
import src.StoryBeasts;
import src.UnknownBeast;
import src.UnknownBeast;

public class Func {
    StoryBeasts storyBeasts = new StoryBeasts();

    /**
     * Помощь в командах.
     *
     * @return
     * @since 1.0
     */
    public String printHelp() {
        return "insert {String key} {element} - добавить новый элемент с заданным ключом" + "\n"
                + "remove_greater {element} - удалить из коллекции все элементы, превышающие заданный" + "\n"
                + "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" + "\n"
                + "save - сохранить коллекцию в файл" + "\n"
                + "info - вывести в стандартный поток вывода информацию о коллекции" + "\n"
                + "remove {String key} - удалить элемент из коллекции по его ключу" + "\n"
                + "import - перечитать коллекцию из файла с заданным путем" + "\n"
                + "story - показать историю зверей" + "\n"
                + "команды и параметры необходимо разделять знаком ;" + "\n";
    }

    /**
     * Начало истории.
     * import - перечитать коллекцию из файла с заданным путем.
     *
     * @return
     * @since 1.0
     */
    public String importt(String path) {
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            InputStreamReader reader = new InputStreamReader(fis);
            int data;
            StringBuilder tempString = new StringBuilder();
            while (true) {
                data = reader.read();
                if (data == -1) break;
                char curChar = (char) data;
                switch (curChar) {
                    case '\n': {
                        storyBeasts.inpStrings.add(tempString.toString());
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
            storyBeasts.beginning();
        } catch (Exception e) {
            return "Неправильный путь к файлу";
        }
        return "Коллекция загрузилась";
    }

    /**
     * show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
     *
     * @return
     * @since 1.0
     */
    public String show() {
        String str = "";
        try {
            List list = new ArrayList(storyBeasts.beasts.entrySet());
            Collections.sort(list, (Map.Entry<String, Beasts> a, Map.Entry<String, Beasts> b) -> a.getValue().getIntName() - b.getValue().getIntName());
            for (Object o : list) {
                str += "\n" + o;
            }
            return str;
        } catch (Exception e) {
            System.err.println("Неправильный ввод");
        }
        return "Что то пошло не так";
    }

    /**
     * Метод save сохраняет коллекцию в файл.
     *
     * @return
     * @since 1.0
     */
    public String save() {
        try {
            try (FileOutputStream fos = new FileOutputStream("/Users/valeriy/Documents/JavaProgramms/JavaForAthletes/src/src/SomeBeasts.json")) {
                List list = new ArrayList(storyBeasts.beasts.entrySet());
                Collections.sort(list, (Map.Entry<String, Beasts> a, Map.Entry<String, Beasts> b) -> a.getValue().getIntName() - b.getValue().getIntName());
                for (Object element : list) {
                    Map.Entry mapEntry = (Map.Entry) element;
                    final char dm = (char) 34;
                    String inptext = "{" + dm + "name" + dm + ":" + dm + mapEntry.getValue() + dm + "},\n";
                    byte[] buffer = inptext.getBytes();
                    fos.write(buffer, 0, buffer.length);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Неправильный ввод");
        }
        backupsave();
        return "Коллекция сохранилась";
    }

    /**
     * Метод info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
     *
     * @return
     * @since 1.0
     */
    public String info() {
        return "Тип коллекции: " + storyBeasts.beasts.getClass() + "\n" +
                "Количество элементов в коллекци: " + storyBeasts.beasts.size() + "\n" +
                "Дата создания: " + storyBeasts.CREATE_DATE + "\n";
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
            if (storyBeasts.beasts.containsKey(text)) {
                storyBeasts.beasts.remove(text);
                str = "*Элемент успешно удален*";
            } else {
                str = "*Неправильный ввод*";
            }
            backupsave();
        } catch (Exception e) {
            str = "*Неправильный ввод*";
        }
        return str;
    }

    /**
     * remove_greater_key {String key}: удалить из коллекции все элементы, ключ которых превышает заданный.
     * Пример: remove_greater 2
     * При вводе {String key} большего чем количество элементов, коллекция не изменится.
     *
     * @param text
     * @return
     * @since 1.0
     */

    public String remove_greater(String text) {
        String ret = null;
        try {
            int size = storyBeasts.beasts.size();
            for (int i = Integer.parseInt(text); i < size; i++) {
                storyBeasts.beasts.remove(storyBeasts.keys.get(i));
            }
            ret = "*Элементы успешно удалены*";

            backupsave();
        } catch (Exception e) {
            System.err.println("Неправильный ввод");
        }
        return ret;
    }

    /**
     * insert {String key} {element}: Метод для добавления нового элемента с заданным ключом.
     * Пример: insert Зверь1 Страшный зверь 1
     * При вводе {String key} {element} другого формата в консоль будет выведено сообщение "Неправильный ввод".
     *
     * @param text
     * @return
     * @since 1.0
     */
    public String insert(String text) {
        String[] arraytext = text.split(" ");
        String flag = "*Неправильный ввод*";
        try {
            if (arraytext[1].contains("Страшный")) {
                storyBeasts.beasts.put(arraytext[0], new ScaryBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                storyBeasts.keys.add(arraytext[0]);
                flag = "*Зверь успешно добавлен*";
            } else if (arraytext[1].contains("Неизвестный")) {
                storyBeasts.beasts.put(arraytext[0], new UnknownBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                storyBeasts.keys.add(arraytext[0]);
                flag = "*Зверь успешно добавлен*";
            } else {
                flag = "*Неправильный ввод*";
            }
        } catch (Exception ex) {
            System.err.println("Неправильный ввод");
        }
        backupsave();
        return flag;
    }
    public void story(){
        Main teller = new Main();
        String [] args = new String[]{"str"};
        teller.main(args);
    }
    public void backupsave() {
        try (FileOutputStream fos = new FileOutputStream("/Users/valeriy/Documents/JavaProgramms/JavaForAthletes/backupfile.json")) {
            List list = new ArrayList(storyBeasts.beasts.entrySet());
            Collections.sort(list, (Map.Entry<String, Beasts> a, Map.Entry<String, Beasts> b) -> a.getValue().getIntName() - b.getValue().getIntName());
            for (Object element : list) {
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


}