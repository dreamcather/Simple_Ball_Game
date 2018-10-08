package geometry;

public class Line {
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    public double getXCoefficient() {
        return xCoefficient;
    }

    public double getYCoefficient() {
        return yCoefficient;
    }

    public double getFreeCoefficient() {
        return freeCoefficient;
    }

    public Line(Point start, Point end) {
        xCoefficient = start.getY() - end.getY();
        yCoefficient = end.getX() - start.getX();
        freeCoefficient = start.getX() * end.getY() - end.getX() * start.getY();
    }

    public Line(Point point, Vector vector) {
        xCoefficient = -vector.getYCoefficient();
        yCoefficient = vector.getXCoefficient();
        freeCoefficient = point.getX() * vector.getYCoefficient() - point.getY() * vector.getXCoefficient();
    }

//    public Point getLineIntersectionPoint(Line line) {
//        double mainDeterminant = line.xCoefficient * yCoefficient - xCoefficient * line.yCoefficient;
//        if(mainDeterminant==0)
//            return null;
//        double xDeterminant = xCoefficient * line.freeCoefficient - line.xCoefficient * freeCoefficient;
//        double yDeterminant = yCoefficient * line.freeCoefficient - line.yCoefficient * freeCoefficient;
//
//        double yCoordinateTouch = xDeterminant / mainDeterminant;
//        double xCoordinateTouch = -yDeterminant / mainDeterminant;
//
//        return new Point(xCoordinateTouch, yCoordinateTouch);
//
//    }

    public Point getLineIntersectionPoint(Point point, Vector vector) {

        Line crossLine = new Line(point, vector);
        return GeometricalCalculation.lineIntersection(this,crossLine);
    }

    public double calculateDistanceToPoint(Point point) {

        return Math.abs(xCoefficient * point.getX() + yCoefficient * point.getY() + freeCoefficient)
                / Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
    }

    public Vector getNormal() {
        Vector res = new Vector(xCoefficient, yCoefficient);
        res.norm();
        return res;
    }

    public Point getProjectionPointToLine(Point point) {

        double x = (yCoefficient * (yCoefficient * point.getX() - xCoefficient * point.getY())
                - xCoefficient * freeCoefficient) / (Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));

        double y = (xCoefficient * (-yCoefficient * point.getX() + xCoefficient * point.getY())
                - yCoefficient * freeCoefficient) / (Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));

        return new Point(x, y);
    }

    public double getSignEquationLine(Point point) {
        return xCoefficient * point.getX() + yCoefficient * point.getY() + freeCoefficient;
    }

    public double getDistanceToPoint(Point point){
        return calculateDistanceToPoint(point);
    }

    public boolean isBelongs(Point point){
        if(Math.abs(getSignEquationLine(point))<0.0001)
            return true;
        return false;
    }

    public int getSign(Point point){
        double equationRes = getSignEquationLine(point);
        if(equationRes>0)
            return 1;
        if(equationRes<0)
            return -1;
        return 0;
    }

}