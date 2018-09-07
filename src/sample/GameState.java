package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GameState {
    AnchorPane gamePanel;
    ArrayList<Ball> gameObjects;
    WallColection wals;
    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        new Hero(0, 0, 10, null, keyboardSubscription);
        gameObjects = new ArrayList();
        wals = new WallColection(4);
    }

    private void collisionWithWalls(Ball ball){

    }

    public void cove(){
        for(int i =0; i< gameObjects.size();i++){

        }

    }

}
