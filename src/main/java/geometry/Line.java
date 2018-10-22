package geometry;

import java.io.Serializable;

public class Line implements Serializable {
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    public double getXCoefficient() {
        return xCoefficient;
    }

    public double getYCoefficient() {
        return yCoefficient;
    }

    public double getFreeCoefficient() {
        return freeCoefficient;
    }

    public Line(MyPoint start, MyPoint end) {
        xCoefficient = start.getY() - end.getY();
        yCoefficient = end.getX() - start.getX();
        freeCoefficient = start.getX() * end.getY() - end.getX() * start.getY();
    }

    public Line(MyPoint point, Vector vector) {
        xCoefficient = -vector.getYCoefficient();
        yCoefficient = vector.getXCoefficient();
        freeCoefficient = point.getX() * vector.getYCoefficient() - point.getY() * vector.getXCoefficient();
    }

    public MyPoint getLineIntersectionPoint(MyPoint point, Vector vector) {

        Line crossLine = new Line(point, vector);
        return GeometricalCalculation.lineIntersection(this, crossLine);
    }

    public double calculateDistanceToPoint(MyPoint point) {

        return Math.abs(xCoefficient * point.getX() + yCoefficient * point.getY() + freeCoefficient)
                / Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
    }

    public Vector getNormal() {
        Vector res = new Vector(xCoefficient, yCoefficient);
        res.norm();
        return res;
    }

    public MyPoint getProjectionPointToLine(MyPoint point) {

        double x = (yCoefficient * (yCoefficient * point.getX() - xCoefficient * point.getY())
                - xCoefficient * freeCoefficient) / (Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));

        double y = (xCoefficient * (-yCoefficient * point.getX() + xCoefficient * point.getY())
                - yCoefficient * freeCoefficient) / (Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));

        return new MyPoint(x, y);
    }

    public double getSignEquationLine(MyPoint point) {
        return xCoefficient * point.getX() + yCoefficient * point.getY() + freeCoefficient;
    }

    public boolean isBelongs(MyPoint point) {
        return Math.abs(getSignEquationLine(point)) < 0.0001;
    }

}