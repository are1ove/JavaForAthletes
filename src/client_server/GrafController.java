package client_server;

import java.awt.*;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GrafController extends HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button showBtn;


    @FXML
    void initialize() {

        /*Group root = new Group();
        Canvas canvas = new Canvas(300, 275); // создаем новый объект Canvas с шириной 300px, и высотой 275px
        root.getChildren().addAll((Collection<? extends Node>) canvas); // добавляем его в корневой контейнер
        GraphicsContext context = canvas.get // и получаем GraphicContext

        context.setFill(Color.BLUE); // устанавливаем цвет
        context.fillOval(10, 20, 50, 50); // рисуем овал с левым верхним углом в точке (10;20) и высотой = ширине = 50px

        context.setFill(Color.GREEN);
        context.fillRect(200, 50, 60, 90); // рисуем прямоугльник 60x90px с левым верним углом в точке (200; 50)

        context.setFill(Color.CRIMSON);
        context.fillPolygon(
                new double[]{100, 160, 80}, // X координаты вершин
                new double[]{70, 90, 220}, // Y координаты вершин
                3 // количество вершин
        );

        context.setFill(Color.INDIGO);
        context.setFont(Font.font("Consolas", FontWeight.BOLD, 18)); // устанавливаем шрифт
        context.fillText("> Hello, Grafika.me!_", 10, 254); // рисуем текст в точке (10, 254)
        */
        /*
        Group root = new Group();
        Canvas canvas = new Canvas(100,300);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Stage primaryStage = new Stage();

        Stop[] stops;
        LinearGradient gradient;

        // outer circle
        stops = new Stop[]{new Stop(0, Color.LIGHTSKYBLUE), new Stop(1, Color.BLUE)};
        gradient = new LinearGradient(0.5, 0, 0.5, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillOval(10, 14, 40, 40);
        gc.fill();
        gc.stroke();

        // Inner circle
        stops = new Stop[]{new Stop(0, Color.BLUE), new Stop(1, Color.LIGHTSKYBLUE)};
        gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillOval(13, 17, 34, 34);
        gc.fill();
        gc.stroke();

        // Circle shadow
        gc.beginPath();
        stops = new Stop[]{new Stop(0, Color.GRAY), new Stop(1, Color.WHITE)};
        gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(gradient);
        gc.fillOval(74, 14, 40, 40);
        gc.fill();
        gc.setStroke(Color.WHITE);



        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }



}




