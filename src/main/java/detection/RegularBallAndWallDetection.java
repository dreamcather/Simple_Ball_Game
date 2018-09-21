package detection;

import object.Wall;
import geometry.Point;
import geometry.Vector;
import object.Ball;

public class RegularBallAndWallDetection extends Detection {
    Ball ball;
    Wall wall;

    public RegularBallAndWallDetection(Ball ball, Wall wall) {
        this.ball = ball;
        this.wall = wall;
    }

    private void normalizePoint(Ball ball, Point point) {
        Vector vector = new Vector(point, ball.getPosition());
        vector.setLength(ball.getRadius());
        Point resPoint = vector.getEndPointVector(point);
        ball.setPosition(resPoint);
    }
    private void normalizeLine(Ball ball) {
        Point collisionPoint = wall.getLine().getProjectionPointToLine(ball.getPosition());
        normalizePoint(ball, collisionPoint);
    }
    public boolean isBetween(Point point) {
        double leftCoefficient = wall.getLeftParallelLine().getSignEquationLine(point);
        double rightCoefficient = wall.getRightParallelLine().getSignEquationLine(point);
        if (leftCoefficient * rightCoefficient <= 0) {
            return true;
        }
        return false;

    }
    @Override
    public boolean detect() {
        if (wall.getStart().getDistanceToPoint(ball.getPosition()) <= ball.getRadius() && (wall.getLine().getSignEquationLine(wall.getStart()) >= 0)) {
            normalizePoint(ball, wall.getStart());
            return true;
        }
        if (wall.getEnd().getDistanceToPoint(ball.getPosition()) <= ball.getRadius() && (wall.getLine().getSignEquationLine(wall.getEnd()) >= 0)) {
            normalizePoint(ball, wall.getEnd());
            return true;
        }
        if ((wall.getLine().calculateDistanceToPoint(ball.getPosition()) <= ball.getRadius()) && (isBetween(ball.getPosition()))) {
            normalizeLine(ball);
            return true;
        }
        return false;
    }
}
