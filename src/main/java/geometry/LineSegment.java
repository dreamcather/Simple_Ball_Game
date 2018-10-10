package geometry;

public class LineSegment {
    private MyPoint start;
    private MyPoint end;
    private Line mainLine;
    private double length;

    public LineSegment(MyPoint start, MyPoint end) {
        this.start = start;
        this.end = end;
        mainLine = new Line(start, end);
        length = GeometricalCalculation.getDistanceBetweenTwoPoint(start, end);
    }

    public MyPoint getStart() {
        return start;
    }

    public MyPoint getEnd() {
        return end;
    }

    public Line getMainLine() {
        return mainLine;
    }

    public boolean isBelong(MyPoint point) {
        if (mainLine.isBelongs(point)) {
            double leftDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(start, point);
            double rightDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(end, point);
            if (leftDistance + rightDistance - length < 0.0001)
                return true;
        }
        return false;
    }

    public double getDistanceToPoint(MyPoint point) {
        double distanceToStart = start.getDistanceToPoint(point);
        double distanceToEnd = end.getDistanceToPoint(point);
        double borderDistance = Math.min(distanceToEnd, distanceToStart);
        MyPoint projectionPoint = GeometricalCalculation.getProjectionPointToLine(mainLine, point);
        if (isBelong(projectionPoint)) {
            return Math.min(borderDistance, GeometricalCalculation.getDistanceBetweenTwoPoint(point, projectionPoint));
        }
        return borderDistance;
    }

}
