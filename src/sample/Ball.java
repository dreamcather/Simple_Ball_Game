package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public abstract class Ball {
    protected double xCoefficient;
    protected double yCoefficient;
    protected double speedOfMotion;
    protected CircleModel gameModel;
    Ball(double _x, double _y, double _speed,AnchorPane anchorPane){
        xCoefficient=_x;
        yCoefficient=_y;
        speedOfMotion=_speed;
    }
    public void move(){
        gameModel.move(xCoefficient*speedOfMotion, yCoefficient*speedOfMotion);
    }
    public double getxCoefficient(){
        return xCoefficient;
    }

    public double getSpeedOfMotion() {
        return speedOfMotion;
    }

    public double getyCoefficient() {
        return yCoefficient;
    }

    public double getXCenter() {
        return gameModel.model.getCenterX();
    }

    public double getYCenter() {
        return gameModel.model.getCenterY();
    }

    public double getRadius() {
        return gameModel.model.getRadius();
    }

    protected void norm(){
        double lenght = Math.sqrt(Math.pow(xCoefficient,2) + Math.pow(yCoefficient,2));
        xCoefficient = xCoefficient/lenght;
        yCoefficient = yCoefficient/lenght;
    }

    public void changeVector(double _xCoefficient,double _yCoefficient){
        xCoefficient =_xCoefficient;
        yCoefficient =_yCoefficient;
        norm();
    }

    public abstract <T> T accept(BallVisitor<T> ballVisitor);
}
