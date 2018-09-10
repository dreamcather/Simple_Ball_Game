package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Point extends Ball {
    Point(double _x, double _y, double _speed, AnchorPane anchorPane) {
        super(_x, _y, _speed, anchorPane);
        gameModel = new CircleModel(30,30,15, Color.CYAN,anchorPane);
    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
