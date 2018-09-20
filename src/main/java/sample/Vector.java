package sample;

public class Vector {
    private double xCoefficient;
    private double yCoefficient;

    public double getXCoefficient() {
        return xCoefficient;
    }

    public double getYCoefficient() {
        return yCoefficient;
    }

    public Vector(double xCoefficient, double yCoefficient) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
    }

    public void norm() {
        double length = Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
        xCoefficient /= length;
        yCoefficient /= length;
    }

    public Vector(Point start, Point end) {
        xCoefficient = end.getX() - start.getX();
        yCoefficient = end.getY() - start.getY();
    }

    public Point addition(Point point) {
        return new Point(point.getX() + xCoefficient, point.getY() + yCoefficient);
    }

    public Vector addition(Vector addVector) {
        Vector res = new Vector(this.xCoefficient + addVector.xCoefficient, this.yCoefficient + addVector.yCoefficient);
        return res;
    }

    public boolean equals(Vector vector) {
        boolean xRes = false;
        boolean yRes = false;
        double eps = 0.00001;
        if (Math.abs(vector.xCoefficient - this.xCoefficient) < eps)
            xRes = true;
        if (Math.abs(vector.yCoefficient - this.yCoefficient) < eps)
            yRes = true;
        return xRes && yRes;
    }

    public Vector getReflection(Vector vector) {
        Point start = new Point(-xCoefficient, -yCoefficient);
        Point collisionPoint = new Point(0, 0);
        Line perpendicularLine = new Line(collisionPoint, vector);
        Vector perpendicularVector = perpendicularLine.getNormal();
        Line parallelLine = new Line(start, perpendicularVector);
        Point middlePoint = perpendicularLine.intersectionLine(parallelLine);
        Vector startToMiddle = new Vector(start, middlePoint);
        Point endPoint = startToMiddle.addition(middlePoint);
        return new Vector(collisionPoint, endPoint);
    }

    public double getLength() {
        return Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
    }

    public void setLength(double length) {
        norm();
        xCoefficient *= length;
        yCoefficient *= length;
    }

}
