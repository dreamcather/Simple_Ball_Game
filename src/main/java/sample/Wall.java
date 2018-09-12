package sample;

public class Wall {

    private Line line;

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
