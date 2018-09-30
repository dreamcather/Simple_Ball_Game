package geometry;

public class GeometricalCalculation {
    public static double getDistanceBetweenTwoPoint(Point first, Point second){
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow((first.getY() - second.getY()), 2));
    }
}
