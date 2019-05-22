package client_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Func;

public class Controller {

    static Socket clientSocket;

    static {
        try {
            clientSocket = new Socket("localhost", 3248);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ObjectOutputStream writer;

    static {
        try {
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ObjectInputStream inFromServer;

    static {
        try {
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String current_user_creator;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar lg_menu;

    @FXML
    private Menu rus_menu;

    @FXML
    private Menu eng_menu;

    @FXML
    private Menu est_menu;

    @FXML
    private Menu swe_menu;

    @FXML
    private Button SignUpBtn;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button LoginBtn;

    @FXML
    void initialize(){
        Func theFunc = new Func();
        LoginBtn.setOnAction(event -> {
            String loginText = LoginField.getText().trim();
            String loginPassword = PasswordField.getText().trim();
            if(!loginText.equals("") && !loginPassword.equals("")) {

                Command cmd = Command.getCommand("login" + ";" + loginText + ";" + loginPassword);
                try {
                    writer.writeObject(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Status status = null;
                try {
                    status = (Status) inFromServer.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String flag = status.message;
                if (status.message.equals("*Вход прошел успешно*")) {
                    current_user_creator = loginText;
                    Stage stage = (Stage) LoginBtn.getScene().getWindow();
                    stage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/app.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root1));
                    stage.show();
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Неправильный логин или пароль");
                    alert1.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
                    alert1.show();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Вы не ввели логин или пароль");
                alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                alert.show();
            }
        });
        rus_menu.setOnAction(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("view/sample.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("ru","RU")));
        });
        SignUpBtn.setOnAction(event -> {
            Stage stage = (Stage) SignUpBtn.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/signUp.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        });

    }
}

