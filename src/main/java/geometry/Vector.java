package geometry;

import java.io.Serializable;

public class Vector implements Serializable {
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
        if (length != 0) {
            xCoefficient /= length;
            yCoefficient /= length;
        }
    }

    public Vector(MyPoint start, MyPoint end) {
        xCoefficient = end.getX() - start.getX();
        yCoefficient = end.getY() - start.getY();
    }

    public MyPoint getEndPointVector(MyPoint start) {
        return new MyPoint(start.getX() + xCoefficient, start.getY() + yCoefficient);
    }

    public void sumVector(Vector addVector) {
        xCoefficient += addVector.getXCoefficient();
        yCoefficient += addVector.getYCoefficient();
    }

    public boolean equals(Vector vector) {
        boolean xRes = false;
        boolean yRes = false;
        double eps = 0.00001;
        if (Math.abs(vector.xCoefficient) - Math.abs(this.xCoefficient) < eps)
            xRes = true;
        if (Math.abs(vector.yCoefficient) - Math.abs(this.yCoefficient) < eps)
            yRes = true;
        return xRes && yRes;
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
