import client_server.Controller;
import client_server.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author ilya aka Chaps & Valeriy aka Stakan
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Locale locale = new Locale("en","AU");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("src.bundle",locale);
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Last Lab!");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

*/
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view/sample.fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("en","AU")));

        Parent fxmlMain = fxmlLoader.load();
        Controller homeController = fxmlLoader.getController();
        primaryStage.setTitle("Last Lab!");
        primaryStage.setScene(new Scene(fxmlMain, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
