package src;

import javax.print.DocFlavor;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import src.ScaryBeast;
import src.ScaryBeast;
import src.StoryBeasts;
import src.StoryBeasts;
import src.UnknownBeast;
import src.UnknownBeast;

public class Func {
    StoryBeasts storyBeasts = new StoryBeasts();

    String enter_user;

    /**
     * Вход пользователя.
     *
     * @return
     * @since 2.0
     */
    public String login (String text) {
        String[] arraytext = text.split(" ");
        String flag = "*Вход прошел успешно*";
        enter_user = arraytext[0];
        String Password = arraytext[1];
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        user.setUsername(enter_user);
        user.setPassword(Password);
        ResultSet result = dataBaseHandler.getUser(user);

        int counter = 0;


        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
            return flag;
        }
        else {
            return "*Неправильный логин или пароль*";
        }

    }



    /**
     * Регистрация пользователя.
     *
     * @return
     * @since 2.0
     */
    public String sign_up (String text) {
        String[] arraytext = text.split(" ");
        String flag = "*Вы успешно зарегистрировались! Пароль отправлен вам на почту*";
        String Login = arraytext[0];
        String Password = getSaltString();
        String Email = arraytext[1];
        User user = new User(Login, Password, Email);
        DataBaseHandler dataBaseHandler = new DataBaseHandler();

        dataBaseHandler.signUpUser(user);
        return flag;
    }

    /**
     * Помощь в командах.
     *
     * @return
     * @since 1.0
     */
    public String printHelp() {
        return "*Команды, доступные без авторизации:*"  + "\n"
                + "login {Username} {Passwrod} - войти в систему" + "\n"
                + "sign_up {Username} {Email} - зарегистрироваться" + "\n"
                + "bye - закончить работу с приложением" + "\n"
                + "printHelp - помощь" + "\n"
                + "команды и параметры необходимо разделять знаком ;" + "\n"
                + "\n"
                + "*Команды доступные с авторизацией:*" + "\n"
                + "insert {String key} {element} - добавить новый элемент с заданным ключом" + "\n"
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
            try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/SomeBeasts.json")) {
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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        try {
            ResultSet result = dataBaseHandler.getremoveAnimal(text, enter_user);
            int row_counter = 0;
            try {
                while (result.next()) {
                    row_counter++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (row_counter >= 1) {
                if (storyBeasts.beasts.containsKey(text)) {
                    storyBeasts.beasts.remove(text);
                    dataBaseHandler.removeAnimal(text, enter_user);
                    dataBaseHandler.signAss(text, enter_user, "remove");
                    str = "*Элемент успешно удален*";
                }
                else{
                    str = "*Неправильный ввод*";
                }
            }
            else {
                str = "*Вы не имеете доступ к удалению этого объекта из БД*";
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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String Animal_name = arraytext[1] + " " + arraytext[2] + " " + arraytext[3];
        try {
            if (!storyBeasts.beasts.containsKey(arraytext[0])) {
                if (arraytext[1].contains("Страшный")) {
                    storyBeasts.beasts.put(arraytext[0], new ScaryBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                    storyBeasts.keys.add(arraytext[0]);
                    dataBaseHandler.signUpAnimal(arraytext[0], Animal_name, enter_user);
                    dataBaseHandler.signAss(arraytext[0], enter_user, "insert");
                    flag = "*Зверь успешно добавлен*";
                } else if (arraytext[1].contains("Неизвестный")) {
                    storyBeasts.beasts.put(arraytext[0], new UnknownBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                    storyBeasts.keys.add(arraytext[0]);
                    dataBaseHandler.signUpAnimal(arraytext[0], Animal_name, enter_user);
                    dataBaseHandler.signAss(arraytext[0], enter_user,"insert");
                    flag = "*Зверь успешно добавлен*";
                } else {
                    flag = "*Неправильный ввод*";
                }
            }
            else{
                flag = "*Объект с таким ключом уже создан*";
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
        try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/backup.json")) {
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

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}