package sample;


public abstract class Ball {
    protected double xCoefficient;
    protected double yCoefficient;
    protected double speedOfMotion;
    protected double xCoordinate;
    protected double yCoordinate;
    private double radius;
    private Vector perpendicularVector;
    protected boolean alive;

    public Ball imune;

    Ball(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius) {
        xCoefficient = _x;
        yCoefficient = _y;
        speedOfMotion = _speed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
        perpendicularVector =null;
        alive =true;
        imune =null;
        norm();
    }

    public void move() {
        xCoordinate += xCoefficient * speedOfMotion;
        yCoordinate += yCoefficient * speedOfMotion;
    }

    public double getRadius() {
        return radius;
    }

    protected void norm() {
        double lenght = Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
        xCoefficient = xCoefficient / lenght;
        yCoefficient = yCoefficient / lenght;
    }

    public Point getFuturePosition() {
        return new Point(xCoordinate + xCoefficient * speedOfMotion, yCoordinate + yCoefficient * speedOfMotion);
    }

    public Point getPosition() {
        return new Point(xCoordinate, yCoordinate);
    }

    public Vector getVector() {
        return new Vector(xCoefficient, yCoefficient);
    }

    public void changeVector(Vector vector) {
        xCoefficient = vector.getxCoefficient();
        yCoefficient = vector.getyCoefficient();
        norm();
    }

    public abstract <T> T accept(BallVisitor<T> ballVisitor);

    public boolean isAlive(){
        return alive;
    }

    public void addPerpendicularVector(Vector vector){
        if(perpendicularVector==null){
            perpendicularVector =vector;
        }
        else{
            perpendicularVector =perpendicularVector.addition(vector);
        }
    }

    public void update(){
        if(perpendicularVector!=null) {
            Vector motionVector = this.getVector();
            Vector res = motionVector.getReflection(perpendicularVector);
            this.changeVector(res);
            perpendicularVector = null;
        }
    }

    public void setSpeedOfMotion(double speedOfMotion) {
        this.speedOfMotion = speedOfMotion;
    }
}
