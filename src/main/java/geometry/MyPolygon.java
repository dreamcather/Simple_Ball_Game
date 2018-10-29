package geometry;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.Serializable;

public class MyPolygon implements Serializable {
    private final MyLineSegment[] segments;
    private final int segmentCount;
    private final transient ConvexHull convexHull;

    public MyPolygon(MyPoint[] points) {
        Coordinate[] otherPoints = new Coordinate[points.length];
        for (int i = 0; i < points.length; i++) {
            otherPoints[i] = points[i].convertPoint().getCoordinate();
        }
        convexHull = new ConvexHull(otherPoints, new GeometryFactory());
        Geometry geometry = convexHull.getConvexHull();
        otherPoints = geometry.getCoordinates();
        MyPoint[] newPoints = new MyPoint[otherPoints.length];
        for (int i = 0; i < otherPoints.length; i++) {
            newPoints[i] = new MyPoint(otherPoints[i]);
        }
        segmentCount = newPoints.length;
        segments = new MyLineSegment[segmentCount];
        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new MyLineSegment(newPoints[i], newPoints[i + 1]);
        }
        if (segmentCount > 1) {
            segments[segmentCount - 1] = new MyLineSegment(newPoints[segmentCount - 1], newPoints[0]);
        }
    }

    public MyLineSegment getSegment(int i) {
        if (i < segmentCount)
            return segments[i];
        return null;
    }

    public int getSegmentCount() {
        return segmentCount;
    }

    public boolean isBelong(MyPoint point) {
        Geometry geometry = convexHull.getConvexHull().intersection(point.convertPoint());
        return !geometry.isEmpty();
    }
}
