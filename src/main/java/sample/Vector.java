package sample;

public class Vector {
    private double xCoefficient;
    private double yCoefficient;
    private double lenght;


    public double getxCoefficient() {
        return xCoefficient;
    }

    public void setxCoefficient(double xCoefficient) {
        this.xCoefficient = xCoefficient;
    }

    public double getyCoefficient() {
        return yCoefficient;
    }

    public void setyCoefficient(double yCoefficient) {
        this.yCoefficient = yCoefficient;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        norm();
        this.lenght = lenght;
        xCoefficient*=lenght;
        yCoefficient*=lenght;
    }

    private void norm(){
        xCoefficient = xCoefficient/lenght;
        yCoefficient = yCoefficient/lenght;
    }

    public Vector(double xCoefficient, double yCoefficient) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
        lenght = Math.sqrt(Math.pow(xCoefficient,2)+Math.pow(yCoefficient,2));
    }

    public Vector(Point start, Point end){
        xCoefficient = end.getX() - start.getX();
        yCoefficient = end.getY() - start.getY();
        lenght = Math.sqrt(Math.pow(xCoefficient,2)+Math.pow(yCoefficient,2));
    }

    public Point addition(Point point) {
        return new Point(point.getX() + xCoefficient, point.getY() + yCoefficient);
    }

    public Vector addition(Vector vector){
        return new Vector(this.xCoefficient + vector.xCoefficient,this.yCoefficient + vector.yCoefficient);
    }

    public Vector substrack(Vector vector){
        return new Vector(this.xCoefficient - vector.xCoefficient,this.yCoefficient - vector.yCoefficient);
    }
}
