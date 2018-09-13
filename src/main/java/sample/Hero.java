package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Hero extends Ball {


    public Hero(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, AnchorPane anchorPane,KeyboardSubscription keyboardSubscription) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, anchorPane);
        gameModel = new CircleModel(xCoordinate,yCoordinate,10, Color.AQUAMARINE,anchorPane);
        keyboardSubscription.subscribeToKey(this::move);
    }

    private void move(KeyCode keyCode){
        if(keyCode == KeyCode.LEFT){
            xCoefficient = xCoefficient*Math.cos(Math.toRadians(15)) - yCoefficient*Math.sin(Math.toRadians(15));
            yCoefficient = xCoefficient*Math.sin(Math.toRadians(15)) +yCoefficient*Math.cos(Math.toRadians(15));
            norm();
        }
        if(keyCode ==KeyCode.RIGHT){
            xCoefficient = xCoefficient*Math.cos(Math.toRadians(-15)) - yCoefficient*Math.sin(Math.toRadians(-15));
            yCoefficient = xCoefficient*Math.sin(Math.toRadians(-15)) +yCoefficient*Math.cos(Math.toRadians(-15));
            norm();
        }
        if(keyCode == KeyCode.UP){
            speedOfMotion+=0.1;
        }
        if(keyCode == KeyCode.DOWN){
            speedOfMotion-=0.1;
        }

    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
