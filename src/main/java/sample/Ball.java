package sample;

import javafx.scene.layout.AnchorPane;

public abstract class Ball {
    protected double xCoefficient;
    protected double yCoefficient;
    protected double speedOfMotion;
    protected double xCoordinate;
    protected double yCoordinate;
    protected CircleModel gameModel;
    protected boolean isAlive;
    Ball(double _x, double _y, double _speed,double xCoordinate, double yCoordinate,AnchorPane anchorPane){
        xCoefficient=_x;
        yCoefficient=_y;
        speedOfMotion=_speed;
        this.xCoordinate =xCoordinate;
        this.yCoordinate = yCoordinate;
        isAlive =true;
    }
    public void move(){
        xCoordinate+=xCoefficient*speedOfMotion;
        yCoordinate+=yCoefficient*speedOfMotion;
        gameModel.move(xCoordinate,yCoordinate);
    }
    public double getRadius() {
        return gameModel.model.getRadius();
    }

    protected void norm(){
        double lenght = Math.sqrt(Math.pow(xCoefficient,2) + Math.pow(yCoefficient,2));
        xCoefficient = xCoefficient/lenght;
        yCoefficient = yCoefficient/lenght;
    }

    public abstract <T> T accept(BallVisitor<T> ballVisitor);

    public Point getFuturePosition(){
        return new Point(xCoordinate+xCoefficient*speedOfMotion,yCoordinate+yCoefficient*speedOfMotion);
    }

    public Point getPosition(){
        return new Point(xCoordinate,yCoordinate);
    }

    public Vector getVector(){
        return new Vector(xCoefficient,yCoefficient);
    }

    public void changeVector(Vector vector){
        xCoefficient = vector.getxCoefficient();
        yCoefficient = vector.getyCoefficient();
        norm();
    }

    public boolean isAlive() {
        return isAlive;
    }
}
