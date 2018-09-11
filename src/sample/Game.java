package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.awt.*;

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
        gameState.addEnemy();
        gameState.addEnemy();
        gameState.addEnemy();
        gameState.addEnemy();
        gameState.addPrize();
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
