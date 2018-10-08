package geometry;

public class LineSegment {
    private Point start;
    private Point end;
    private Line mainLine;
    private double length;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
        mainLine = new Line(start, end);
        length = GeometricalCalculation.getDistanceBetweenTwoPoint(start, end);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Line getMainLine() {
        return mainLine;
    }

    public boolean isBelong(Point point) {
        if (mainLine.isBelongs(point)) {
            double leftDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(start, point);
            double rightDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(end, point);
            if (leftDistance + rightDistance - length < 0.0001)
                return true;
        }
        return false;
    }

    public double getDistanceToPoint(Point point) {
        double distanceToStart = start.getDistanceToPoint(point);
        double distanceToEnd = end.getDistanceToPoint(point);
        double borderDistance = Math.min(distanceToEnd, distanceToStart);
        Point projectionPoint = GeometricalCalculation.getProjectionPointToLine(mainLine, point);
        if (isBelong(projectionPoint)) {
            return Math.min(borderDistance, GeometricalCalculation.getDistanceBetweenTwoPoint(point, projectionPoint));
        }
        return borderDistance;
    }

}
