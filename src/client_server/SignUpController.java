package client_server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client_server.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.Func;

public class SignUpController extends Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button previousBtn;

    @FXML
    private Button RegisterBtn;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField EmailField;

    @FXML
    void initialize() {
        Func theFunc = new Func();
        RegisterBtn.setOnAction(event -> {
            String registerText = LoginField.getText().trim();
            String registerEmail = EmailField.getText().trim();

            if(!registerText.equals("") && !registerEmail.equals("")) {
                Command cmd = Command.getCommand("sign_up" + ";" + registerText + ";" + registerEmail);
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, flag);
                alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                alert.show();
            }
            else{
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Вы не ввели логин или пароль");
                alert1.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
                alert1.show();
            }
        });



        previousBtn.setOnAction(event -> {

            Stage stage = (Stage) previousBtn.getScene().getWindow();
            stage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/sample.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Регистрация");
            stage.setScene(new Scene(root1));
            stage.show();
        });

    }
}
