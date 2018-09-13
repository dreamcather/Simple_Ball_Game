package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Enemy extends Ball {

    public Enemy(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, AnchorPane anchorPane) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, anchorPane);
        gameModel = new CircleModel(xCoordinate, yCoordinate, 5, Color.RED, anchorPane);

    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

}
