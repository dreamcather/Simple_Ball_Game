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
        scene = new Scene(layout, 700, 520);
        Game gameState = new game.Game(layout);
        Rectangle leftBorder = new Rectangle(500,0,15,500);
        leftBorder.setFill(Color.DARKBLUE);
        Rectangle rightBorder = new Rectangle(0,500,515,15);
        rightBorder.setFill(Color.DARKBLUE);
        layout.getChildren().add(leftBorder);
        layout.getChildren().add(rightBorder);
        Rider reader =new Rider("output.txt",gameState);
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
