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
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Main extends Application {

    Stage window;
    Robot robot;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Game");

        AnchorPane layout = new AnchorPane();
        robot = new Robot();
        ArrayList<Circle> models = new ArrayList<Circle>();


        scene = new Scene(layout , 700, 420);
        Circle player = new Circle(100,100,10);
        Circle enemy1 = new Circle(40,40,10,Color.RED);
        Circle enemy2 = new Circle(110,140,10,Color.GREEN);
        player.setFill(new Color(0.5,0.5,0.5,0.5));
        Rectangle field = new Rectangle(10,10,400,400);
        field.setFill(Color.WHITE);
        field.setStroke(Color.BLACK);
        field.setStrokeWidth(10);
        layout.getChildren().add(field);
        layout.getChildren().add(player);
        models.add(player);
        KeyModule motion = new KeyModule(player);
        Enemy_List enm = new Enemy_List(10,layout,player,lb);
        GameState gm = new GameState(layout);
        //Ball tmp =new Ball(1,1,3,gm);
        //tmp.model.setCenterX(100);
        //tmp.model.setCenterY(100);
        //tmp.model.setFill(Color.GREEN);
        //tmp.model.setRadius(10);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //tmp.Move();
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
