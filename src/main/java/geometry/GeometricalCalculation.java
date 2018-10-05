package geometry;

public class GeometricalCalculation {
    public static double getDistanceBetweenTwoPoint(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow((first.getY() - second.getY()), 2));
    }

    public static Point lineIntersection(Line first, Line second) {
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

        return new Point(xCoordinateTouch, yCoordinateTouch);
    }

    public static Vector vectorSum(Vector first, Vector second) {
        return new Vector(first.getXCoefficient() + second.getXCoefficient(),
                          first.getYCoefficient() + second.getYCoefficient());
    }

    public static Vector vectorReflection(Vector reflected,Vector vector){
        Point start = new Point(-reflected.getXCoefficient(), -reflected.getYCoefficient());
        Point collisionPoint = new Point(0, 0);
        Line perpendicularLine = new Line(collisionPoint, vector);
        Vector perpendicularVector = perpendicularLine.getNormal();
        Line parallelLine = new Line(start, perpendicularVector);
        Point middlePoint = GeometricalCalculation.lineIntersection(perpendicularLine,parallelLine);
        Vector startToMiddle = new Vector(start, middlePoint);
        Point endPoint = startToMiddle.getEndPointVector(middlePoint);
        return new Vector(collisionPoint, endPoint);
    }

    public static Point lineSegmentIntersection(LineSegment first,LineSegment second){
        Point res = lineIntersection(first.getMainLine(),second.getMainLine());
        if(res == null)
            return null;
        if(first.isBelong(res)&&second.isBelong(res))
            return res;
        return null;
    }

}
