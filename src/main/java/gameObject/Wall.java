package gameObject;

import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.LineSegment;
import geometry.MyPoint;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;

public class Wall extends  GameObject{

    private Line mainLine;
    public LineSegment lineSegment;
    private Line leftParallelLine;
    private Line rightParallelLine;
    private MyPoint start;
    private MyPoint end;

    public Wall(MyPoint _start, MyPoint _end) {

        mainLine = new Line(_start, _end);
        start = _start;
        end = _end;
        leftParallelLine = new Line(start, mainLine.getNormal());
        rightParallelLine = new Line(end, mainLine.getNormal());
        lineSegment = new LineSegment(start,end);
        type ="W";

    }

    public Wall(String string) {
        String[] substr = string.split("  ");
        start = new MyPoint(Double.parseDouble(substr[0]),Double.parseDouble(substr[1]));
        end = new MyPoint(Double.parseDouble(substr[2]),Double.parseDouble(substr[3]));
        type = "W";
    }

    public Line getLine() {
        return mainLine;
    }

    public Line getLeftParallelLine() {
        return leftParallelLine;
    }

    public Line getRightParallelLine() {
        return rightParallelLine;
    }

    public MyPoint getStart() {
        return start;
    }

    public MyPoint getEnd() {
        return end;
    }

    public <T> T collision(ObjectInteractionVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }

    @Override
    public void changeVector() {

    }

    @Override
    public void move(MotionControl motionControl) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public MyPoint getPosition() {
        return new MyPoint(0,0);
    }

    @Override
    public String toString() {
        String res = new String(start.getX()+"  "+start.getY()+ "  "+end.getX()+"  "+end.getY()+"\n");
        return res;
    }

    public boolean isBetween(MyPoint point)
    {
        if(leftParallelLine.getSignEquationLine(point)*
                rightParallelLine.getSignEquationLine(point)<0)
            return true;
        return false;
    }

    public MyPoint getIntersectionPoint(Wall wall){
        MyPoint res = GeometricalCalculation.lineIntersection(mainLine,wall.getLine());
        if(res==null)
            return null;
        if(leftParallelLine.getSignEquationLine(res)*
                rightParallelLine.getSignEquationLine(res)<0) {
            if(wall.isBetween(res))
            return res;
        }
        return null;
    }
}
