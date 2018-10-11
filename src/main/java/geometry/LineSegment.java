package geometry;

public interface LineSegment {
    MyPoint getStart();

    MyPoint getEnd();

    Line getMainLine();

    boolean isBelong(MyPoint point);

    double getDistanceToPoint(MyPoint point);
}
