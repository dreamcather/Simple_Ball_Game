package visual;

import geometry.GeometricalCalculation;
import geometry.LineSegment;
import geometry.MyPoint;

import java.util.ArrayList;

public class Camera {
    MyPoint position;
    int weight = 250;
    double xOffset;
    double minX;
    double maxX;
    double minY;
    double maxY;
    LineSegment upHorizontal;
    LineSegment downHorizontal;
    LineSegment leftVertical;
    LineSegment rightVertical;
    MyPoint leftUpPoint;
    MyPoint leftDownPoint;
    MyPoint rightUpPoint;
    MyPoint rightDownPoint;

    public Camera(MyPoint position, double offset, double minX, double maxX, double minY, double maxY) {
        this.position = position;
        this.xOffset = offset;
        this.minX = minX + weight;
        this.maxX = maxX - weight;
        this.minY = minY + weight;
        this.maxY = maxY - weight;
        leftUpPoint = new MyPoint(0, 0);
        leftDownPoint = new MyPoint(0, 2 * weight);
        rightUpPoint = new MyPoint(2 * weight, 0);
        rightDownPoint = new MyPoint(2 * weight, 2 * weight);
        upHorizontal = new LineSegment(rightUpPoint, leftUpPoint);
        downHorizontal = new LineSegment(leftDownPoint, rightDownPoint);
        leftVertical = new LineSegment(leftUpPoint, leftDownPoint);
        rightVertical = new LineSegment(rightDownPoint, rightUpPoint);
    }

    public MyPoint transformPoint(MyPoint point) {
        return new MyPoint(point.getX() - position.getX() + weight,
                point.getY() - position.getY() + weight);
    }

    public boolean isVisible(MyPoint point) {
        MyPoint transformPoint = transformPoint(point);
        if ((transformPoint.getY() <= 2 * weight) &&
                (transformPoint.getY() >= 0) &&
                (transformPoint.getX() <= 2 * weight) &&
                (transformPoint.getX() >= 0)) {
            return true;
        }
        return false;
    }

    public MyPoint getPoint(MyPoint point, LineSegment lineSegment) {
        MyPoint current;
        ArrayList<MyPoint> list = new ArrayList<>();
        if ((current = GeometricalCalculation.lineSegmentIntersection(upHorizontal,lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(downHorizontal,lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(leftVertical,lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(rightVertical,lineSegment)) != null)
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

    public MyPoint getPosition() {
        return position;
    }

    public void setPosition(MyPoint position) {
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
