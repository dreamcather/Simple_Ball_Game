package gameObject;

import geometry.LineSegment;
import geometry.Point;
import geometry.Vector;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public class ClosedWall extends GameObject {
    int segmentCount;
    LineSegment[] segments;

    public ClosedWall(Point[] points) {
        segmentCount = points.length;
        segments = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new LineSegment(points[i], points[i + 1]);
        }
        segments[segmentCount - 1] = new LineSegment(points[segmentCount - 1], points[0]);
        type ="CW";
    }

    public double getDistance(Point point) {
        double min = segments[0].getDistanceToPoint(point);
        for (int i = 1; i < segmentCount; i++) {
            if (min > segments[i].getDistanceToPoint(point))
                min = segments[i].getDistanceToPoint(point);
        }
        return min;
    }

    public Vector getResultPerpendicularVector(Ball ball) {
        Vector res = new Vector(0, 0);
        for (int i = 0; i < segmentCount; i++) {
            if (segments[i].getDistanceToPoint(ball.getPosition()) < ball.getRadius())
                res.sumVector(segments[i].getMainLine().getNormal());
        }
        return res;
    }

    public Point[] getPoints(){
        Point[]res = new Point[segmentCount];
        for(int i=0;i<segmentCount;i++){
            res[i] = segments[i].getStart();
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
