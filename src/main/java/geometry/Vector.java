package geometry;

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
        if(length!=0) {
            xCoefficient /= length;
            yCoefficient /= length;
        }
    }

    public Vector(Point start, Point end) {
        xCoefficient = end.getX() - start.getX();
        yCoefficient = end.getY() - start.getY();
    }

    public Point getEndPointVector(Point start) {
        return new Point(start.getX() + xCoefficient, start.getY() + yCoefficient);
    }

    public Vector sumVector(Vector addVector) {
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

    public Vector getReflectionThroughVector(Vector vector) {
        Point start = new Point(-xCoefficient, -yCoefficient);
        Point collisionPoint = new Point(0, 0);
        Line perpendicularLine = new Line(collisionPoint, vector);
        Vector perpendicularVector = perpendicularLine.getNormal();
        Line parallelLine = new Line(start, perpendicularVector);
        Point middlePoint = perpendicularLine.getLineIntersectionPoint(parallelLine);
        Vector startToMiddle = new Vector(start, middlePoint);
        Point endPoint = startToMiddle.getEndPointVector(middlePoint);
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
