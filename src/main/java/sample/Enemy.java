package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Enemy extends Ball {

    Enemy(double _x, double _y, double _speed, AnchorPane anchorPane) {
        super(_x, _y, _speed, anchorPane);
        int randomX = (int)(Math.random()*200);
        int randomY =(int)(Math.random()*200);
        gameModel = new CircleModel(randomX,randomY,5, Color.RED,anchorPane);
    }

    @Override
    public  <T> T accept( BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

}
