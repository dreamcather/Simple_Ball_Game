package geometry;

import org.locationtech.jts.geom.*;

import java.io.Serializable;

public class MyPoint implements Serializable {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MyPoint(MyPoint tmp) {
        x = tmp.getX();
        y = tmp.getY();
    }

    public MyPoint(Coordinate coordinate) {
        x = coordinate.getX();
        y = coordinate.getY();
    }

    public double getDistanceToPoint(MyPoint point) {
        return Math.sqrt(Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2));
    }

    public boolean equals(MyPoint point) {
        double eps = 0.000001;
        boolean resX = false;
        boolean resY = false;
        if (Math.abs(point.x - this.x) < eps)
            resX = true;
        if (Math.abs(point.y - this.y) < eps)
            resY = true;
        return resX && resY;

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point convertPoint() {
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new Coordinate(x, y));
    }
}
