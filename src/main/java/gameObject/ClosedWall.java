package gameObject;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import geometry.GeometricalCalculation;
import geometry.MyPoint;
import geometry.MyPolygon;
import geometry.Vector;
import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.ClosedWallVisualInformation;
import visual.visualInformation.VisualInformation;

public class ClosedWall extends GameObject {
    private final MyPolygon polygon;

    public ClosedWall(MyPoint[] points, int key) {
        super(key);
        polygon = new MyPolygon(points);
        type = "CW";
    }

    public double getDistance(MyPoint point) {
        MyPoint nearestPoint = GeometricalCalculation.getNearestPointOfPolygon(polygon, point);
        return GeometricalCalculation.getDistanceBetweenTwoPoint(nearestPoint, point);
    }

    public MyPolygon getPolygon() {
        return polygon;
    }

    public Vector getResultPerpendicularVector(Ball ball) {
        Vector res = new Vector(0, 0);
        for (int i = 0; i < polygon.getSegmentCount(); i++) {
            if (polygon.getSegment(i).getDistanceToPoint(ball.getPosition()) <= ball.getRadius())
                res.sumVector(polygon.getSegment(i).getMainLine().getNormal());
        }
        return res;
    }

    private MyPoint[] getPoints() {
        MyPoint[] res = new MyPoint[polygon.getSegmentCount()];
        for (int i = 0; i < polygon.getSegmentCount(); i++) {
            res[i] = polygon.getSegment(i).getStart();
        }
        return res;
    }

    @Override
    public <T> T collision(ObjectInteractionVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public void changeVector() {

    }

    @Override
    public void move() {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        MyPoint[] points = getPoints();
        MyPoint[] res = new MyPoint[points.length];
        for (int i = 0; i < res.length; i++) {
            MyPoint current = camera.transformPoint(points[i]);
            current.setX(current.getX() + camera.getOffset());
            current.setY(current.getY() + camera.getOffset());
            res[i] = current;
        }
        Coordinate[] coordinates = new Coordinate[res.length];
        for (int i = 0; i < res.length; i++) {
            coordinates[i] = res[i].convertPoint().getCoordinate();
        }
        Geometry geometry = new ConvexHull(coordinates, new GeometryFactory()).getConvexHull();
        Geometry cameraGeometry = camera.getConvexHull().getConvexHull();
        Geometry resGeometry = geometry.intersection(cameraGeometry);
        coordinates = resGeometry.getCoordinates();
        if (coordinates.length < 3)
            return null;
        MyPoint[] resPointArray = new MyPoint[coordinates.length - 1];
        for (int i = 0; i < coordinates.length - 1; i++) {
            resPointArray[i] = new MyPoint(coordinates[i]);
        }
        return new ClosedWallVisualInformation(resPointArray, this);
    }
}
