package client_server;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import src.Const;
import src.DataBaseHandler;
import src.Func;
import src.ObjectsTable;

public class HomeController extends Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ObjectsTable> Objects_table;

    @FXML
    private TableColumn<ObjectsTable, Integer> idColumn;

    @FXML
    private TableColumn<ObjectsTable, String> keyColumn;

    @FXML
    private TableColumn<ObjectsTable, String> nameColumn;

    @FXML
    private TableColumn<ObjectsTable, String> creatorColumn;

    @FXML
    private Button save_btn;

    @FXML
    private Button remove_btn;

    @FXML
    private Button insert_btn;

    @FXML
    private TextField Edit_name;

    @FXML
    private TextField Edit_key;

    @FXML
    private Button exit_btn;

    ObservableList<ObjectsTable>  oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tests", "pg", "studs");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM "+ Const.ANIMAL_TABLE);
            while (rs.next()){
                oblist.add(new ObjectsTable(rs.getInt("id_objects"),rs.getString("key"),
                        rs.getString("name"), rs.getString("creator")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        keyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        creatorColumn.setCellValueFactory(new PropertyValueFactory<>("creator"));

        Objects_table.setEditable(true);
        Objects_table.setItems(oblist);
        keyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        keyColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ObjectsTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ObjectsTable, String> event) {
                        event.getTableView().getItems().get(
                                event.getTablePosition().getColumn()
                        ).setKey(event.getNewValue());
                    }
                }
        );
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ObjectsTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ObjectsTable, String> event) {
                        event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).setName(event.getNewValue());
                    }
                }
        );
        creatorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        creatorColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ObjectsTable, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<ObjectsTable, String> event) {
                        event.getTableView().getItems().get(
                                event.getTablePosition().getColumn()
                        ).setCreator(event.getNewValue());
                    }
                }
        );

        insert_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                oblist.add(new ObjectsTable(1, Edit_key.getText(), Edit_name.getText(), "creator"));
            }
        });
    }
}

