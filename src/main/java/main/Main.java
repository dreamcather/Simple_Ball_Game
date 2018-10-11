package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import save.Rider;
import game.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        scene = new Scene(layout, 750, 600);
        Game gameState = new game.Game(layout);
        Rectangle leftBorder = new Rectangle(550, 50, 15, 500);
        leftBorder.setFill(Color.DARKBLUE);
        Rectangle rightBorder = new Rectangle(50, 550, 515, 15);
        rightBorder.setFill(Color.DARKBLUE);
        Rectangle downBorder = new Rectangle(35, 35, 15, 530);
        downBorder.setFill(Color.DARKBLUE);
        Rectangle upBorder = new Rectangle(50, 35, 515, 15);
        upBorder.setFill(Color.DARKBLUE);
        layout.getChildren().add(leftBorder);
        layout.getChildren().add(rightBorder);
        layout.getChildren().add(upBorder);
        layout.getChildren().add(downBorder);
        Rider reader = new Rider("output.txt", gameState);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameState.mouseClick(event);
            }
        });
        window.setScene(scene);
        window.show();
        gameState.start();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    if (gameState.isActive())
                        gameState.stop();
                    else
                        gameState.start();
                }
                gameState.click(event.getCode());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
