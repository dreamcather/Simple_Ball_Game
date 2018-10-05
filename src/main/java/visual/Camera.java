package visual;

import gameObject.Wall;
import geometry.GeometricalCalculation;
import geometry.Point;

import java.util.ArrayList;

public class Camera {
    Point position;
    int weight = 250;
    double xOffset;
    double minX;
    double maxX;
    double minY;
    double maxY;
    Wall upHorizontal;
    Wall downHorizontal;
    Wall leftVertical;
    Wall rightVertical;
    Point leftUpPoint;
    Point leftDownPoint;
    Point rightUpPoint;
    Point rightDownPoint;

    public Camera(Point position, double offset, double minX, double maxX, double minY, double maxY) {
        this.position = position;
        this.xOffset = offset;
        this.minX = minX + weight;
        this.maxX = maxX - weight;
        this.minY = minY + weight;
        this.maxY = maxY - weight;
        leftUpPoint = new Point(0, 0);
        leftDownPoint = new Point(0, 2 * weight);
        rightUpPoint = new Point(2 * weight, 0);
        rightDownPoint = new Point(2 * weight, 2 * weight);
        upHorizontal = new Wall(rightUpPoint, leftUpPoint);
        downHorizontal = new Wall(leftDownPoint, rightDownPoint);
        leftVertical = new Wall(leftUpPoint, leftDownPoint);
        rightVertical = new Wall(rightDownPoint, rightUpPoint);
    }

    public Point transformPoint(Point point) {
        return new Point(point.getX() - position.getX() + weight,
                point.getY() - position.getY() + weight);
    }

    public boolean isVisible(Point point) {
        Point transformPoint = transformPoint(point);
        if ((transformPoint.getY() <= 2 * weight) &&
                (transformPoint.getY() >= 0) &&
                (transformPoint.getX() <= 2 * weight) &&
                (transformPoint.getX() >= 0)) {
            return true;
        }
        return false;
    }

    public Point getPoint(Point point, Wall wall) {
        Point current;
        ArrayList<Point> list = new ArrayList<>();
        if ((current = upHorizontal.getIntersectionPoint(wall)) != null)
            list.add(current);
        if ((current = downHorizontal.getIntersectionPoint(wall)) != null)
            list.add(current);
        if ((current = leftVertical.getIntersectionPoint(wall)) != null)
            list.add(current);
        if ((current = rightVertical.getIntersectionPoint(wall)) != null)
            list.add(current);
        if (list.size() == 0)
            return null;
        double distance = GeometricalCalculation.getDistanceBetweenTwoPoint(list.get(0), point);
        current = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            double curDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(list.get(i), point);
            if (curDistance < distance) {
                current = list.get(i);
                distance = curDistance;
            }
        }
        return current;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        double x = position.getX();
        double y = position.getY();
        if (x < minX)
            x = minX;
        if (y < minY)
            y = minY;
        if (x > maxX)
            x = maxX;
        if (y > maxY)
            y = maxY;
        this.position.setX(x);
        this.position.setY(y);
    }

    public double getXOffset() {
        return xOffset;
    }
}
