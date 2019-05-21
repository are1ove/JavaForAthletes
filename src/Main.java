import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

/**
 * @author ilya aka Chaps & Valeriy aka Stakan
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.Locale");
        Parent root = load(getClass().getResource("/view/sample.fxml"), resourceBundle);
        primaryStage.setTitle("Last Lab!");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



