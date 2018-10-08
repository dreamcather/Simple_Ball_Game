package geometry;

import java.util.ArrayList;
import java.util.LinkedList;

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

    public static Vector vectorReflection(Vector reflected, Vector vector) {
        Point start = new Point(-reflected.getXCoefficient(), -reflected.getYCoefficient());
        Point collisionPoint = new Point(0, 0);
        Line perpendicularLine = new Line(collisionPoint, vector);
        Vector perpendicularVector = perpendicularLine.getNormal();
        Line parallelLine = new Line(start, perpendicularVector);
        Point middlePoint = GeometricalCalculation.lineIntersection(perpendicularLine, parallelLine);
        Vector startToMiddle = new Vector(start, middlePoint);
        Point endPoint = startToMiddle.getEndPointVector(middlePoint);
        return new Vector(collisionPoint, endPoint);
    }

    public static Point lineSegmentIntersection(LineSegment first, LineSegment second) {
        Point res = lineIntersection(first.getMainLine(), second.getMainLine());
        if (res == null)
            return null;
        if (first.isBelong(res) && second.isBelong(res))
            return res;
        return null;
    }

    public static Point getProjectionPointToLine(Line line, Point point) {
        double norma = Math.pow(line.getXCoefficient(), 2) + Math.pow(line.getYCoefficient(), 2);
        double x = (line.getYCoefficient()
                * (line.getYCoefficient() * point.getX() - line.getXCoefficient() * point.getY())
                - line.getXCoefficient() * line.getFreeCoefficient()) / norma;

        double y = (line.getXCoefficient()
                * (-line.getYCoefficient() * point.getX() + line.getXCoefficient() * point.getY())
                - line.getYCoefficient() * line.getFreeCoefficient()) / norma;

        return new Point(x, y);
    }
    
    public static Point getNearestPointOfLineSegment(LineSegment lineSegment,Point point){
        double distanceToStart = lineSegment.getStart().getDistanceToPoint(point);
        double distanceToEnd = lineSegment.getEnd().getDistanceToPoint(point);
        Point projectionPointOnLine = getProjectionPointToLine(lineSegment.getMainLine(),point);
        if(lineSegment.isBelong(projectionPointOnLine)){
            double distanceToMain = lineSegment.getMainLine().calculateDistanceToPoint(point);
            if(distanceToMain == Math.min(distanceToMain,Math.min(distanceToStart,distanceToEnd)))
                return projectionPointOnLine;
        }
        if(distanceToEnd<distanceToStart)
            return lineSegment.getEnd();
        return lineSegment.getStart();
    }

    public static Point getNearestPointOfPolygon(Polygon polygon,Point point){
        Point minPoint =getNearestPointOfLineSegment(polygon.getSegment(0),point);
        double minDistance = getDistanceBetweenTwoPoint(point,minPoint);
        for(int i=1;i<polygon.getSegmentCount();i++){
            Point currentPoint = getNearestPointOfLineSegment(polygon.getSegment(i),point);
            double currentDistance = getDistanceBetweenTwoPoint(currentPoint,point);
            if(minDistance>currentDistance){
                minDistance = currentDistance;
                minPoint=currentPoint;
            }
        }
        return minPoint;
    }

    public static Point getEndVectorPoint(Point start,Vector vector){
        return new Point(start.getX()+vector.getXCoefficient(),start.getY()+vector.getYCoefficient());
    }

    public static Point lineSegmentAndLineIntersection(LineSegment lineSegment,Line line){
        Point intersectionPoint = lineIntersection(line,lineSegment.getMainLine());
        if(intersectionPoint!=null){
            if(lineSegment.isBelong(intersectionPoint))
                return intersectionPoint;
        }
        return null;
    }

    public static Point pushingPointAway(Point controlPoint, Point movePoint, double distance){
        if(getDistanceBetweenTwoPoint(controlPoint,movePoint)>0.001) {
            Vector vector = new Vector(controlPoint, movePoint);
            vector.setLength(distance);
            return getEndVectorPoint(controlPoint, vector);
        }
        return movePoint;
    }

    public static Polygon narrowPolygon(Polygon polygon,Line line){
        ArrayList<Point> intersectionPointList = new ArrayList<>();
        for(int i=0;i<polygon.getSegmentCount();i++){
            Point curent = lineSegmentAndLineIntersection(polygon.getSegment(i),line);
            if(curent!=null)
                intersectionPointList.add(curent);
        }
        Point[] points = polygon.getPoints();
        if(intersectionPointList.size()==0){
            for(int i=0;i<points.length;i++){
               if(line.getSign(points[i])<0)
                   return polygon;
            }
            return new Polygon(new Point[]{});
        }
        Point firstIntersectionPoint = intersectionPointList.get(0);
        Point secondIntersectionPoint = intersectionPointList.get(1);
        return polygon;

    }

}
