package src;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs {
    Connection dbconnection;

    public Connection getDbconnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        dbconnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        return dbconnection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_USERNAME + "," +
                Const.USERS_PASSWORD + "," +
                Const.USERS_Email + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(insert);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getEmail());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser (User user){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1, user.getUsername());
            prSt.setString(2, user.getPassword());
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet isUsername (String user_name){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1, user_name);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet isUserEmail(String email){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_Email + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1, email);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAnimals (){
        ResultSet resultSet = null;

        String select = "SELECT " + Const.ANIMAL_KEY + "," +
                Const.ANIMAL_NAME + "," + Const.ANIMAL_CREATOR +
                " FROM " + Const.ANIMAL_TABLE;
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void signAss(String key, String user, String action){
        String ass_insert = "INSERT INTO " + Const.ASS_TABLE + "(" +
                Const.ASS_OBJECT_KEY + "," +
                Const.ASS_USERNAME + "," +
                Const.ASS_ACTION + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement prSt1 = getDbconnection().prepareStatement(ass_insert);
            prSt1.setString(1, key);
            prSt1.setString(2, user);
            prSt1.setString(3, action);
            prSt1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpAnimal(String key, String name, String user_login){
        String insert = "INSERT INTO " + Const.ANIMAL_TABLE + "(" +
                Const.ANIMAL_KEY + "," +
                Const.ANIMAL_NAME + "," +
                Const.ANIMAL_CREATOR + ")" +
                "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(insert);
            prSt.setString(1, key);
            prSt.setString(2, name);
            prSt.setString(3, user_login);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void renameAnimal(String key, String name){
        String update = "UPDATE " + Const.ANIMAL_TABLE + " SET " +
                Const.ANIMAL_NAME + "=?" + " WHERE " +
                Const.ANIMAL_KEY + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(update);
            prSt.setString(1, name);
            prSt.setString(2, key);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getremoveAnimal(String key, String user_login){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.ANIMAL_TABLE + " WHERE " +
                Const.ANIMAL_KEY + "=? AND " + Const.ANIMAL_CREATOR + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1, key);
            prSt.setString(2, user_login);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getAnimalKey(String key){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.ANIMAL_TABLE + " WHERE " +
                Const.ANIMAL_KEY + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(select);
            prSt.setString(1, key);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void removeAnimal(String key, String user_login){
        String delete = "DELETE FROM " + Const.ANIMAL_TABLE  + " WHERE " +
                Const.ANIMAL_KEY + "=? AND " +
                Const.ANIMAL_CREATOR + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(delete);
            prSt.setString(1, key);
            prSt.setString(2, user_login);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void remove_greater (int count, String user_login){

        String delete_greater = "DELETE FROM " + Const.ANIMAL_TABLE  + " WHERE " +
                Const.ANIMAL_ID + ">? AND " +
                Const.ANIMAL_CREATOR + "=?";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(delete_greater);
            prSt.setInt(1, count);
            prSt.setString(2, user_login);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
