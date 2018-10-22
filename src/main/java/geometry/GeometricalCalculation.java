package geometry;

public class GeometricalCalculation {
    public static double getDistanceBetweenTwoPoint(MyPoint first, MyPoint second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow((first.getY() - second.getY()), 2));
    }

    public static MyPoint lineIntersection(Line first, Line second) {
        double mainDeterminant = first.getXCoefficient() * second.getYCoefficient()
                - second.getXCoefficient() * first.getYCoefficient();
        if (mainDeterminant == 0)
            return null;
        double xDeterminant = second.getXCoefficient() * first.getFreeCoefficient()
                - first.getXCoefficient() * second.getFreeCoefficient();
        double yDeterminant = second.getYCoefficient() * first.getFreeCoefficient()
                - first.getYCoefficient() * second.getFreeCoefficient();

        double yCoordinateTouch = xDeterminant / mainDeterminant;
        double xCoordinateTouch = -yDeterminant / mainDeterminant;

        return new MyPoint(xCoordinateTouch, yCoordinateTouch);
    }

    public static Vector vectorSum(Vector first, Vector second) {
        return new Vector(first.getXCoefficient() + second.getXCoefficient(),
                          first.getYCoefficient() + second.getYCoefficient());
    }

    public static Vector vectorReflection(Vector reflected, Vector vector) {
        MyPoint start = new MyPoint(-reflected.getXCoefficient(), -reflected.getYCoefficient());
        MyPoint collisionPoint = new MyPoint(0, 0);
        Line perpendicularLine = new Line(collisionPoint, vector);
        Vector perpendicularVector = perpendicularLine.getNormal();
        Line parallelLine = new Line(start, perpendicularVector);
        MyPoint middlePoint = GeometricalCalculation.lineIntersection(perpendicularLine, parallelLine);
        assert middlePoint != null;
        Vector startToMiddle = new Vector(start, middlePoint);
        MyPoint endPoint = startToMiddle.getEndPointVector(middlePoint);
        return new Vector(collisionPoint, endPoint);
    }

    public static MyPoint lineSegmentIntersection(LineSegment first, LineSegment second) {
        MyPoint res = lineIntersection(first.getMainLine(), second.getMainLine());
        if (res == null)
            return null;
        if (first.isBelong(res) && second.isBelong(res))
            return res;
        return null;
    }

    public static MyPoint getProjectionPointToLine(Line line, MyPoint point) {
        double norma = Math.pow(line.getXCoefficient(), 2) + Math.pow(line.getYCoefficient(), 2);
        double x = (line.getYCoefficient()
                * (line.getYCoefficient() * point.getX() - line.getXCoefficient() * point.getY())
                - line.getXCoefficient() * line.getFreeCoefficient()) / norma;

        double y = (line.getXCoefficient()
                * (-line.getYCoefficient() * point.getX() + line.getXCoefficient() * point.getY())
                - line.getYCoefficient() * line.getFreeCoefficient()) / norma;

        return new MyPoint(x, y);
    }

    private static MyPoint getNearestPointOfLineSegment(LineSegment lineSegment, MyPoint point) {
        double distanceToStart = lineSegment.getStart().getDistanceToPoint(point);
        double distanceToEnd = lineSegment.getEnd().getDistanceToPoint(point);
        MyPoint projectionPointOnLine = getProjectionPointToLine(lineSegment.getMainLine(), point);
        if (lineSegment.isBelong(projectionPointOnLine)) {
            double distanceToMain = lineSegment.getMainLine().calculateDistanceToPoint(point);
            if (distanceToMain == Math.min(distanceToMain, Math.min(distanceToStart, distanceToEnd)))
                return projectionPointOnLine;
        }
        if (distanceToEnd < distanceToStart)
            return lineSegment.getEnd();
        return lineSegment.getStart();
    }

    public static MyPoint getNearestPointOfPolygon(MyPolygon polygon, MyPoint point) {
        MyPoint minPoint = getNearestPointOfLineSegment(polygon.getSegment(0), point);
        double minDistance = getDistanceBetweenTwoPoint(point, minPoint);
        for (int i = 1; i < polygon.getSegmentCount(); i++) {
            MyPoint currentPoint = getNearestPointOfLineSegment(polygon.getSegment(i), point);
            double currentDistance = getDistanceBetweenTwoPoint(currentPoint, point);
            if (minDistance > currentDistance) {
                minDistance = currentDistance;
                minPoint = currentPoint;
            }
        }
        return minPoint;
    }

    private static MyPoint getEndVectorPoint(MyPoint start, Vector vector) {
        return new MyPoint(start.getX() + vector.getXCoefficient(), start.getY() + vector.getYCoefficient());
    }

    public static MyPoint pushingPointAway(MyPoint controlPoint, MyPoint movePoint, double distance) {
        if (getDistanceBetweenTwoPoint(controlPoint, movePoint) > 0.001) {
            Vector vector = new Vector(controlPoint, movePoint);
            vector.setLength(distance);
            return getEndVectorPoint(controlPoint, vector);
        }
        return movePoint;
    }

}
