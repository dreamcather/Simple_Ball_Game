package gameObject;

import geometry.GeometricalCalculation;
import geometry.MyPoint;
import geometry.Vector;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.VisualInformation;

public abstract class Ball extends GameObject {
    protected double xCoefficient;
    protected double yCoefficient;
    protected double speedOfMotion;
    protected double xCoordinate;
    protected double yCoordinate;
    protected int radius;
    private Vector perpendicularVector;
    protected boolean alive;

    Ball(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, int radius, int key) {
        super(key);
        xCoefficient = _x;
        yCoefficient = _y;
        speedOfMotion = _speed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
        perpendicularVector = null;
        alive = true;
        norm();
    }

    public void move() {
        xCoordinate += xCoefficient * speedOfMotion;
        yCoordinate += yCoefficient * speedOfMotion;
    }

    public double getRadius() {
        return radius;
    }

    private void norm() {
        double length = Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
        xCoefficient = xCoefficient / length;
        yCoefficient = yCoefficient / length;
    }

    public MyPoint getFuturePosition() {
        return new MyPoint(xCoordinate + xCoefficient * speedOfMotion, yCoordinate + yCoefficient * speedOfMotion);
    }

    public double getSpeedOfMotion() {
        return speedOfMotion;
    }

    public MyPoint getPosition() {
        return new MyPoint(xCoordinate, yCoordinate);
    }

    public MyPoint getPosition(MyPoint point) {
        return new MyPoint(xCoordinate + point.getX(), yCoordinate + point.getY());
    }

    public void setPosition(MyPoint point) {
        xCoordinate = point.getX();
        yCoordinate = point.getY();
    }

    public Vector getVector() {
        return new Vector(xCoefficient, yCoefficient);
    }

    public void changeVector(Vector vector) {
        xCoefficient = vector.getXCoefficient();
        yCoefficient = vector.getYCoefficient();
        norm();
    }

    public abstract <T> T collision(ObjectInteractionVisitor<T> ballVisitor);

    public boolean isAlive() {
        return alive;
    }

    public void sumPerpendicularVector(Vector vector) {
        if (perpendicularVector == null) {
            perpendicularVector = vector;
        } else {
            perpendicularVector.sumVector(vector);
        }
    }

    public void changeVector() {
        if (perpendicularVector != null) {
            Vector motionVector = this.getVector();
            Vector res = GeometricalCalculation.vectorReflection(motionVector, perpendicularVector);
            this.changeVector(res);
            perpendicularVector = null;
        }
    }

    public void setSpeedOfMotion(double speedOfMotion) {
        this.speedOfMotion = speedOfMotion;
    }

    public void setLiveStatus(boolean alive) {
        this.alive = alive;
    }

    public String toString() {
        return this.type + " " + xCoefficient + " " + yCoefficient + " " + speedOfMotion + " " + xCoordinate + " "
                + yCoordinate + " " + radius;
    }

    public VisualInformation isVisible(Camera camera) {
        if (camera.isVisible(getPosition())) {
            MyPoint position = camera.transformPoint(getPosition());
            position.setX(position.getX() + camera.getOffset());
            position.setY(position.getY() + camera.getOffset());
            return new BallVisualInformation(position, radius,this);
        } else {
            Wall wall = new Wall(camera.transformPoint(getPosition()), camera.transformPoint(camera.getPosition()), 0);
            MyPoint res = camera.getPoint(camera.transformPoint(getPosition()), wall.lineSegment);
            if (res == null)
                return null;
            res.setX(res.getX() + camera.getOffset());
            res.setY(res.getY() + camera.getOffset());
            return new BallVisualInformation(res, radius,this);
        }
    }
}
