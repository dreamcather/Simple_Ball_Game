package sample;

public class Wall {
    private Point start;

    private Point end;

    private Line line;

    private Vector normal;

    public double xLineCoefficient;

    public double yLineCoefficient;

    public double freeLineCoefficient;


    Wall(Point _start, Point _end) {

        line = new Line(_start,_end);

    }


    public Line getLine() {
        return line;
    }

    public double calculateDistanceToPoint(Point point){

        return line.calculateDistanceToPoint(point);
    }
}
