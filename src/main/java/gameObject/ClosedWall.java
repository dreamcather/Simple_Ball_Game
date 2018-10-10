package gameObject;

import geometry.*;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;

public class ClosedWall extends GameObject {
    MyPolygon polygon;

    public ClosedWall(MyPoint[] points) {
        polygon = new MyPolygon(points);
        type ="CW";
    }

    public double getDistance(MyPoint point) {
        MyPoint nearestPoint = GeometricalCalculation.getNearestPointOfPolygon(polygon,point);
        return GeometricalCalculation.getDistanceBetweenTwoPoint(nearestPoint,point);
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

    public MyPoint[] getPoints(){
        MyPoint[]res = new MyPoint[polygon.getSegmentCount()];
        for(int i=0;i<polygon.getSegmentCount();i++){
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
    public void move(MotionControl motionControl) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public MyPoint getPosition() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
