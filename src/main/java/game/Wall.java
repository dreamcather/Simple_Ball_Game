package game;

import geometry.Line;
import geometry.Point;
import geometry.Vector;
import object.Ball;

public class Wall {

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

    public double calculateDistanceToPoint(Point point) {

        return mainLine.calculateDistanceToPoint(point);
    }

    private double orientation(Point point, Line line) {
        return line.getXCoefficient() * point.getX() + line.getYCoefficient() * point.getY()
                + line.getFreeCoefficient();
    }

    private void normalizePoint(Ball ball, Point point) {
        Vector vector = new Vector(point, ball.getPosition());
        vector.setLength(ball.getRadius());
        Point resPoint = vector.getEndPointVector(point);
        ball.setPosition(resPoint);
    }

    private void normalizeLine(Ball ball) {
        Point collisionPoint = mainLine.getProjectionPointToLine(ball.getPosition());
        normalizePoint(ball, collisionPoint);
    }

    boolean isCollisionWithBallAndNormalize(Ball ball) {
        if (start.getDistanceToPoint(ball.getPosition()) <= ball.getRadius() && (this.getLine().getSignEquationLine(start) >= 0)) {
            normalizePoint(ball, start);
            return true;
        }
        if (end.getDistanceToPoint(ball.getPosition()) <= ball.getRadius() && (this.getLine().getSignEquationLine(end) >= 0)) {
            normalizePoint(ball, end);
            return true;
        }
        if ((calculateDistanceToPoint(ball.getPosition()) <= ball.getRadius()) && (isBetween(ball.getPosition()))) {
            normalizeLine(ball);
            return true;
        }
        return false;
    }

    public boolean isBetween(Point point) {
        double leftCoefficient = orientation(point, leftParallelLine);
        double rightCoefficient = orientation(point, rightParallelLine);
        if (leftCoefficient * rightCoefficient <= 0) {
            return true;
        }
        return false;

    }
}
