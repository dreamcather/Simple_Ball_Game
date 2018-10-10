package geometry;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

public class MyPolygon {
    private LineSegment[] segments;
    private int segmentCount;

    public MyPolygon(MyPoint[] points) {
        Coordinate[] otherPoints = new Coordinate[points.length];
        for(int i=0;i<points.length;i++){
            otherPoints[i] = points[i].convertPoint().getCoordinate();
        }
        ConvexHull convexHull = new ConvexHull(otherPoints,new GeometryFactory());
        Geometry geometry =convexHull.getConvexHull();
        otherPoints = geometry.getCoordinates();
        MyPoint[] newPoints = new MyPoint[otherPoints.length];
        for(int i = 0;i<otherPoints.length;i++){
            newPoints[i] = new MyPoint(otherPoints[i]);
        }
        segmentCount = newPoints.length;
        segments = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new LineSegment(newPoints[i], newPoints[i + 1]);
        }
        if(segmentCount>1) {
            segments[segmentCount - 1] = new LineSegment(newPoints[segmentCount - 1], newPoints[0]);
        }
    }

    public LineSegment getSegment(int i){
        if(i<segmentCount)
            return segments[i];
        return null;
    }

    public int getSegmentCount() {
        return segmentCount;
    }

    public MyPoint[] getPoints(){
        MyPoint[]res = new MyPoint[getSegmentCount()];
        for(int i=0;i<getSegmentCount();i++){
            res[i] = getSegment(i).getStart();
        }
        return res;
    }
}
