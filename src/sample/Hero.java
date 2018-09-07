package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class Hero extends Ball {

    Hero(double _x, double _y, double _speed, GameState _game, KeyboardSubscription keyboardSubscription) {
        super(_x, _y, _speed);
        keyboardSubscription.subscribeToKey(this::move);
    }
    private void move(KeyCode keyCode){

    }
}
