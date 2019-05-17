package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Properties;

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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultname = dataBaseHandler.isUsername(Login);
        ResultSet resultemail = dataBaseHandler.isUserEmail(Email);
        int countername = 0;
        int counteremail = 0;
        try {
            while (resultname.next()) {
                countername++;
            }
            while (resultemail.next()) {
                counteremail++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (countername == 0 & counteremail == 0) {
            final Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("/Users/ilya/Documents/Учеба/Программирование/lab5/JavaForAthletes/mail.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            try {
                message.setFrom(new InternetAddress("ilonach003@gmail.com"));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                message.setSubject("Your new password");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                message.setText(Password);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            Transport transport = null;
            try {
                transport = mailSession.getTransport();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            try {
                transport.connect("ilonach003@gmail.com","03012003ch");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                transport.sendMessage(message,message.getAllRecipients());
                transport.close();
                User user = new User(Login, Password, Email);
                dataBaseHandler.signUpUser(user);
            } catch (MessagingException e) {
                flag = "*Неправильный формат почты*";
            }
        }
        else if (countername == 1 & counteremail == 0){
            flag = "*Пользователь с таким именем уже существует*";
        }
        else if ((countername == 1 || countername == 0) & counteremail == 1){
            flag = "*Вы уже зарегистрированы, проверьте свою почту, письмо с паролем могло попасть в спам*";
        }

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
                //+ "remove_greater {element} - удалить из коллекции все элементы, превышающие заданный" + "\n"
                + "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении" + "\n"
                + "save - сохранить коллекцию в файл" + "\n"
                + "info - вывести в стандартный поток вывода информацию о коллекции" + "\n"
                + "remove {String key} - удалить элемент из коллекции по его ключу" + "\n"
                //+ "import - перечитать коллекцию из файла с заданным путем" + "\n"
                //+ "story - показать историю зверей" + "\n"
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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.getAnimals();
        try {
            while (resultSet.next()) {
                String key = resultSet.getString("key");
                String name = resultSet.getString("name");
                String creator = resultSet.getString("creator");

                str += "\n key:" + key + "; " + "name:" + name + "; " + "creator:" + creator;
            }
            return str;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return "Что то пошло не так";
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
        try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/SomeBeasts.json")) {
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            ResultSet resultSet = dataBaseHandler.getAnimals();
            try {
                while (resultSet.next()) {
                    String key = resultSet.getString("key");
                    String name = resultSet.getString("name");
                    String creator = resultSet.getString("creator");
                    final char dm = (char) 34;
                    String inptext = "{" + dm + "key" + dm + ":" + dm + key + dm + ","
                            + dm + "name" + dm + ":" + dm + name + dm + "," + dm + "creator" + dm + ":" + dm + creator + dm + "},\n";
                    byte[] buffer = inptext.getBytes();
                    fos.write(buffer, 0, buffer.length);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.getAnimals();
        int counter = 0;
        try {
            while (resultSet.next()) {
                counter+=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Тип коллекции: " + storyBeasts.beasts.getClass() + "\n" +
                "Количество элементов в коллекци: " + counter + "\n" +
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
            int count = Integer.parseInt(text);
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            dataBaseHandler.remove_greater(count, enter_user);
            dataBaseHandler.signAss("-", enter_user, "remove_greater " + text);
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
        if (arraytext.length == 4){
            String Animal_name = arraytext[1] + " " + arraytext[2] + " " + arraytext[3];
            try {
                ResultSet resultSet = dataBaseHandler.getremoveAnimal(arraytext[0], enter_user);
                ResultSet resultSet1 = dataBaseHandler.getAnimalKey(arraytext[0]);
                int row_counter = 0;
                int row_counter_key = 0;
                try {
                    while (resultSet.next()) {
                        row_counter++;
                    }
                    while (resultSet1.next()) {
                        row_counter_key++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (row_counter == 0 & row_counter_key == 0) {
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
                else if (row_counter == 1 & row_counter_key == 1) {
                    if (arraytext[1].contains("Страшный")) {
                        storyBeasts.beasts.put(arraytext[0], new ScaryBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                        storyBeasts.keys.add(arraytext[0]);
                        dataBaseHandler.renameAnimal(arraytext[0], Animal_name);
                        dataBaseHandler.signAss(arraytext[0], enter_user, "rename");
                        flag = "*Зверь успешно обновлен*";
                    } else if (arraytext[1].contains("Неизвестный")) {
                        storyBeasts.beasts.put(arraytext[0], new UnknownBeast(arraytext[1] + " " + arraytext[2] + " " + arraytext[3]));
                        storyBeasts.keys.add(arraytext[0]);
                        dataBaseHandler.renameAnimal(arraytext[0], Animal_name);
                        dataBaseHandler.signAss(arraytext[0], enter_user, "rename");
                        flag = "*Зверь успешно обновлен*";
                    } else {
                        flag = "*Неправильный ввод*";
                    }
                }
                else{
                    flag = "*Этот объект создан другим пользователем, вы не имеете права его изменять*";
                }
            } catch (Exception ex) {
                System.err.println("Неправильный ввод");
            }
        }
        else{
            flag = "*Неправильный ввод*";

        }

        backupsave();
        return flag;
    }

    public void backupsave() {
        try (FileOutputStream fos = new FileOutputStream("/Users/ilya/Desktop/backup.json")) {
            DataBaseHandler dataBaseHandler = new DataBaseHandler();
            ResultSet resultSet = dataBaseHandler.getAnimals();
            try {
                while (resultSet.next()) {
                    String key = resultSet.getString("key");
                    String name = resultSet.getString("name");
                    String creator = resultSet.getString("creator");
                    final char dm = (char) 34;
                    String inptext = "{" + dm + "key" + dm + ":" + dm + key + dm + ","
                            + dm + "name" + dm + ":" + dm + name + dm + "," + dm + "creator" + dm + ":" + dm + creator + dm + "},\n";
                    byte[] buffer = inptext.getBytes();
                    fos.write(buffer, 0, buffer.length);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
