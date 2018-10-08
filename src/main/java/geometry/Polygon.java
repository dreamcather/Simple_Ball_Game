package geometry;

public class Polygon {
    private LineSegment[] segments;
    private int segmentCount;

    public Polygon(Point[] points) {
        segmentCount = points.length;
        segments = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount - 1; i++) {
            segments[i] = new LineSegment(points[i], points[i + 1]);
        }
        if(segmentCount>1) {
            segments[segmentCount - 1] = new LineSegment(points[segmentCount - 1], points[0]);
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

    public Point[] getPoints(){
        Point[]res = new Point[getSegmentCount()];
        for(int i=0;i<getSegmentCount();i++){
            res[i] = getSegment(i).getStart();
        }
        return res;
    }
}
