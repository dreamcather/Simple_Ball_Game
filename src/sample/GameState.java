package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GameState {
    AnchorPane gamePanel;
    ArrayList<Ball> gameObjects;
    WallColection walls;
    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        new Hero(0, 0, 10, null, keyboardSubscription);
        gameObjects = new ArrayList();
        walls = new WallColection(4);
    }


    private void collisionWithWalls(Ball ball){
        double radToWal=0;
        for(int i=0;i< walls.collection.length;i++){
            Wall curentWall = walls.collection[i];
            if(curentWall.calculateDistanceToPoint(ball.getXCenter()+ball.getxCoefficient()*ball.getSpeedOfMotion()
                    ,ball.getYCenter() +ball.getyCoefficient()*ball.getSpeedOfMotion() )<ball.getRadius()){


            }

        }
    }

    public void move(){
        for(int i =0; i< gameObjects.size();i++){
            collisionWithWalls(gameObjects.get(i));
        }

    }

}
