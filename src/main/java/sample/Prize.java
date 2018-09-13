package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Prize extends Ball
{

    Prize(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, AnchorPane anchorPane) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, anchorPane);
        gameModel = new CircleModel(xCoordinate,yCoordinate,5,Color.CYAN,anchorPane);
    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    public void eating(){
        isAlive =false;
    }
}
