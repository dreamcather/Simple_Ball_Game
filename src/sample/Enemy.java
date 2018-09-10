package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Enemy extends Ball {

    Enemy(double _x, double _y, double _speed, AnchorPane anchorPane) {
        super(_x, _y, _speed, anchorPane);
        gameModel = new CircleModel(50,50,5, Color.RED,anchorPane);
    }
}
