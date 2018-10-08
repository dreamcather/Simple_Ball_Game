package gameObject;

import geometry.*;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public class ClosedWall extends GameObject {
    Polygon polygon;

    public ClosedWall(Point[] points) {
        polygon = new Polygon(points);
        type ="CW";
    }

    public double getDistance(Point point) {
        Point nearestPoint = GeometricalCalculation.getNearestPointOfPolygon(polygon,point);
        return GeometricalCalculation.getDistanceBetweenTwoPoint(nearestPoint,point);
    }

    public Polygon getPolygon() {
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

    public Point[] getPoints(){
        Point[]res = new Point[polygon.getSegmentCount()];
        for(int i=0;i<polygon.getSegmentCount();i++){
            res[i] = polygon.getSegment(i).getStart();
        }
        return res;
    }

    @Override
    public <T> T collision(ObjectInteractVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public void changeVector() {

    }

    @Override
    public void move(MotionControl motionControl) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
