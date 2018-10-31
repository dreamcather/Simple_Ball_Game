package visual;

import java.util.ArrayList;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import geometry.GeometricalCalculation;
import geometry.LineSegment;
import geometry.MyLineSegment;
import geometry.MyPoint;

public class Camera {
    private final MyPoint position;
    private final int size = 200;
    private final double offset;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final MyLineSegment upHorizontal;
    private final MyLineSegment downHorizontal;
    private final MyLineSegment leftVertical;
    private final MyLineSegment rightVertical;
    private final ConvexHull convexHull;

    public Camera(MyPoint position,
                  double offset,
                  double minX,
                  double playingFieldHeight,
                  double playingFieldWidth,
                  double maxY) {
        this.position = position;
        this.offset = offset;
        this.minX = minX + size;
        this.maxX = playingFieldHeight - size;
        this.minY = playingFieldWidth + size;
        this.maxY = maxY - size;
        MyPoint leftUpPoint = new MyPoint(0, 0);
        MyPoint leftDownPoint = new MyPoint(0, 2 * size);
        MyPoint rightUpPoint = new MyPoint(2 * size, 0);
        MyPoint rightDownPoint = new MyPoint(2 * size, 2 * size);
        Coordinate[] coordinates = new Coordinate[4];
        coordinates[0] = leftUpPoint.convertPoint().getCoordinate();
        coordinates[1] = leftDownPoint.convertPoint().getCoordinate();
        coordinates[2] = rightUpPoint.convertPoint().getCoordinate();
        coordinates[3] = rightDownPoint.convertPoint().getCoordinate();
        for (int i = 0; i < 4; i++) {
            coordinates[i].setX(coordinates[i].getX() + this.offset);
            coordinates[i].setY(coordinates[i].getY() + this.offset);
        }
        convexHull = new ConvexHull(coordinates, new GeometryFactory());
        upHorizontal = new MyLineSegment(rightUpPoint, leftUpPoint);
        downHorizontal = new MyLineSegment(leftDownPoint, rightDownPoint);
        leftVertical = new MyLineSegment(leftUpPoint, leftDownPoint);
        rightVertical = new MyLineSegment(rightDownPoint, rightUpPoint);
    }

    public MyPoint transformPoint(MyPoint point) {
        return new MyPoint(point.getX() - position.getX() + size, point.getY() - position.getY() + size);
    }

    public boolean isVisible(MyPoint point) {
        MyPoint transformPoint = transformPoint(point);
        return (transformPoint.getY() <= 2 * size) && (transformPoint.getY() >= 0)
                && (transformPoint.getX() <= 2 * size) && (transformPoint.getX() >= 0);
    }

    public MyPoint getPoint(MyPoint point, LineSegment lineSegment) {
        MyPoint current;
        ArrayList<MyPoint> list = new ArrayList<>();
        if ((current = GeometricalCalculation.lineSegmentIntersection(upHorizontal, lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(downHorizontal, lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(leftVertical, lineSegment)) != null)
            list.add(current);
        if ((current = GeometricalCalculation.lineSegmentIntersection(rightVertical, lineSegment)) != null)
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

    public void setPosition(MyPoint point) {
        double x = point.getX();
        double y = point.getY();
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

    public double getOffset() {
        return offset;
    }

    public ConvexHull getConvexHull() {
        return convexHull;
    }

    public int getSize() {
        return size;
    }
}
