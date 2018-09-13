package sample;

public class Wall {

    private Line mainLine;
    private Line leftParalelLine;
    private Line rightParalelLine;
    private Point start;
    private Point end;

    Wall(Point _start, Point _end) {

        mainLine = new Line(_start,_end);
        start =_start;
        end = _end;
        leftParalelLine = new Line(start,mainLine.getNormal());
        rightParalelLine = new Line(end,mainLine.getNormal());

    }


    public Line getLine() {
        return mainLine;
    }

    public double calculateDistanceToPoint(Point point){

        return mainLine.calculateDistanceToPoint(point);
    }

    private double orientation(Point point, Line line) {
        return line.getxCoefficient() * point.getX() + line.getyCoefficient() * point.getY() +
                line.getFreeCoefficient();
    }

    public boolean isBetween(Point point){
        double leftCoefficient = orientation(point,leftParalelLine);
        double rightCoefficient = orientation(point, rightParalelLine);
        if(leftCoefficient*rightCoefficient<=0){
            return true;
        }
        return false;

    }
}
