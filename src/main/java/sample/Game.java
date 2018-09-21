package sample;

import geometry.Point;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import object.Enemy;
import object.Hero;
import object.Prize;

public class Game extends Application {

    Stage window;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        AnchorPane layout = new AnchorPane();
        scene = new Scene(layout, 700, 520);
        game.Game gameState = new game.Game(layout);
        Hero hero = new Hero(-1,
                             0,
                             3,
                             310,
                             50,
                             15,
                             consumer -> scene.setOnKeyPressed(event -> consumer.accept(event.getCode())));
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hero.setMouseEvent(event);
            }
        });
        gameState.addEnemy(new Enemy(1, 0, 1, 50, 50, 15));
        gameState.addEnemy(new Enemy(1, 2, 1, 100, 50, 15));
        gameState.addEnemy(new Enemy(1, 3, 1, 150, 50, 15));
        gameState.addEnemy(new Enemy(1, 4, 1, 200, 50, 15));
        gameState.addPrize(new Prize(-1, 1, 3, 100, 20, 15));
        gameState.addHero(hero);
        gameState.addWall(new Point(0, 500), new Point(0, 0));
        gameState.addWall(new Point(500, 0), new Point(500, 500));
        gameState.addWall(new Point(0, 0), new Point(500, 0));
        gameState.addWall(new Point(500, 500), new Point(0, 500));
        gameState.addWall(new Point(250, 200), new Point(200, 250));
        gameState.addWall(new Point(200, 250), new Point(250, 300));
        gameState.addWall(new Point(250, 300), new Point(300, 250));
        gameState.addWall(new Point(300, 250), new Point(250, 200));
        gameState.addWall(new Point(0, 50), new Point(50, 0));
        gameState.addWall(new Point(400, 400), new Point(450, 350));
        gameState.addWall(new Point(500, 400), new Point(450, 350));
        gameState.addWall(new Point(500, 500), new Point(400, 400));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameState.update();
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
