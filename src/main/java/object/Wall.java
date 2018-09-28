package object;

import detection.ObjectDetectVisitor;
import geometry.Line;
import geometry.Point;
import geometry.Vector;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public class Wall extends  GameObject{

    private Line mainLine;
    private Line leftParallelLine;
    private Line rightParallelLine;
    private Point start;
    private Point end;

    public Wall(Point _start, Point _end) {

        mainLine = new Line(_start, _end);
        start = _start;
        end = _end;
        leftParallelLine = new Line(start, mainLine.getNormal());
        rightParallelLine = new Line(end, mainLine.getNormal());

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

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public <T> T collisionDetection(ObjectDetectVisitor<T> objectDetectVisitor) {
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

    public <T> T collisionReaction(ObjectInteractVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }
}
