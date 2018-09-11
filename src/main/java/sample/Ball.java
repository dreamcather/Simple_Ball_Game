package sample;

import javafx.scene.layout.AnchorPane;

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

    public Point getFuturePosition(){
        return new Point(gameModel.getxCenter()+xCoefficient*speedOfMotion,gameModel.getyCenter()+yCoefficient*speedOfMotion);
    }

    public Point getPosition(){
        return new Point(gameModel.getxCenter(),gameModel.getyCenter());
    }

    public Vector getVector(){
        return new Vector(xCoefficient,yCoefficient);
    }

    public void changeVector(Vector vector){
        xCoefficient = vector.getxCoefficient();
        yCoefficient = vector.getyCoefficient();
        norm();
    }
}
