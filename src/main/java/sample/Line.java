package sample;

public class Line {
    private double xCoefficient;
    private double yCoefficient;
    private double freeCoefficient;

    public Line(Point start, Point end) {
        xCoefficient = start.getY() - end.getY();
        yCoefficient = end.getX() - start.getX();
        freeCoefficient = start.getX()*end.getY() - end.getX()*start.getY();
    }

    public Line(Point point, Vector vector) {
        xCoefficient = -vector.getyCoefficient();
        yCoefficient = vector.getxCoefficient();
        freeCoefficient = point.getX()*vector.getyCoefficient() - point.getY()*vector.getxCoefficient();
    }

    public Point intersectionLine(Line line){
        double mainDeterminant = line.xCoefficient*yCoefficient -  xCoefficient*line.yCoefficient;
        double xDeterminant = xCoefficient*line.freeCoefficient - line.xCoefficient*freeCoefficient;
        double yDeterminant = yCoefficient*line.freeCoefficient - line.yCoefficient*freeCoefficient;

        double yCoordinateTouch = xDeterminant/mainDeterminant;
        double xCoordinateTouch = -yDeterminant/mainDeterminant;

        return new Point(xCoordinateTouch,yCoordinateTouch);

    }

    public Point intersectionLine(Point point, Vector vector){

        Line crossLine = new Line(point,vector);
        return  intersectionLine(crossLine);
    }

    public double calculateDistanceToPoint(Point point) {

        return Math.abs(xCoefficient * point.getX() + yCoefficient * point.getY() + freeCoefficient)
                / Math.sqrt(Math.pow(xCoefficient, 2) + Math.pow(yCoefficient, 2));
    }

    public Vector getNormal(){
        return new Vector(xCoefficient,yCoefficient);
    }

    public Point getProectionPoint(Point point){

        double x = (yCoefficient*(yCoefficient*point.getX() - xCoefficient*point.getY())-xCoefficient*freeCoefficient)/
                (Math.pow(xCoefficient,2)+Math.pow(yCoefficient,2));

        double y = (xCoefficient*(-yCoefficient*point.getX() + xCoefficient*point.getY())-yCoefficient*freeCoefficient)/
                (Math.pow(xCoefficient,2)+Math.pow(yCoefficient,2));

        return new Point(x,y);
    }


}
