package src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() throws NoSuchAlgorithmException {
        MessageDigest sha224 = MessageDigest.getInstance("SHA-224");
        byte[] bytes = sha224.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02X", b));
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
