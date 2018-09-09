package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Game extends Application {

    Stage window;
    Robot robot;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        scene = new Scene(layout , 700, 420);
        layout.getChildren().add(new Line(0,200,0,0));
        layout.getChildren().add(new Line(200,0,200,200));
        layout.getChildren().add(new Line(0,0,200,0));
        layout.getChildren().add(new Line(200,200,0,200));
        GameState gameState = new GameState(layout, consumer -> scene.setOnKeyPressed(event -> consumer.accept(event.getCode())));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameState.move();
            }
        };
        window.setScene(scene);
        window.show();
        timer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
