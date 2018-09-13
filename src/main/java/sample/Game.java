package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Game extends Application {

    Stage window;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        scene = new Scene(layout, 700, 520);
        layout.getChildren().add(new Line(0, 500, 0, 0));
        layout.getChildren().add(new Line(500, 0, 500, 500));
        layout.getChildren().add(new Line(0, 0, 500, 0));
        layout.getChildren().add(new Line(500, 500, 0, 500));
        layout.getChildren().add(new Line(250, 200, 200, 250));
        layout.getChildren().add(new Line(200, 250, 250, 300));
        layout.getChildren().add(new Line(250, 300, 300, 250));
        layout.getChildren().add(new Line(300, 250, 250, 200));
        GameState gameState = new GameState(layout,
                                            consumer -> scene.setOnKeyPressed(event -> consumer.accept(event.getCode())));
        Hero hero = new Hero(1,1,1,100,100,
                layout,consumer -> scene.setOnKeyPressed(event -> consumer.accept(event.getCode())));
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
