package client_server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> keyColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> creatorColumn;

    @FXML
    void initialize() {
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'app.fxml'.";
        assert keyColumn != null : "fx:id=\"keyColumn\" was not injected: check your FXML file 'app.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'app.fxml'.";
        assert creatorColumn != null : "fx:id=\"creatorColumn\" was not injected: check your FXML file 'app.fxml'.";

    }
}


