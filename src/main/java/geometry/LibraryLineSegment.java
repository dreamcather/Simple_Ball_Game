package geometry;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class LibraryLineSegment implements LineSegment {
    private final org.locationtech.jts.geom.LineSegment lineSegment;

    public LibraryLineSegment(MyPoint start, MyPoint end) {
        lineSegment = new org.locationtech.jts.geom.LineSegment(start.convertPoint().getCoordinate(),
                                                                end.convertPoint().getCoordinate());
    }

    @Override
    public MyPoint getStart() {
        return new MyPoint(lineSegment.p0);
    }

    @Override
    public MyPoint getEnd() {
        return new MyPoint(lineSegment.p1);
    }

    @Override
    public Line getMainLine() {
        return new Line(getStart(), getEnd());
    }

    @Override
    public boolean isBelong(MyPoint point) {
        Geometry geometry = lineSegment.toGeometry(new GeometryFactory());
        Point point1 = point.convertPoint();
        Geometry geometry1 = geometry.intersection(point1);
        return !geometry1.isEmpty();
    }

    @Override
    public double getDistanceToPoint(MyPoint point) {
        return lineSegment.distance(point.convertPoint().getCoordinate());
    }
}
