package sample;

import javafx.scene.shape.Circle;

public abstract class Ball {
    private double xCoefficient;
    private double yCoefficient;
    private double speedOfMotion;
    private Model gameModel;
    Ball(double _x, double _y, double _speed){
        xCoefficient=_x;
        yCoefficient=_y;

        speedOfMotion=_speed;
    }
    public void move(){
        gameModel.Move(xCoefficient*speedOfMotion, yCoefficient*speedOfMotion);
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
        return 0;
    }

    public double getYCenter() {
        return 0;
    }

    public double getRadius() {
        return 0;
    }
}
