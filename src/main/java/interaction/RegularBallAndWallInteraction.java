package interaction;

import gameObject.Ball;
import gameObject.Wall;
import geometry.GeometricalCalculation;
import geometry.MyPoint;
import geometry.Vector;

public class RegularBallAndWallInteraction implements Interaction {
    private final Ball ball;
    private final Wall wall;

    public RegularBallAndWallInteraction(Ball ball, Wall wall) {
        this.ball = ball;
        this.wall = wall;
    }

    private void normalizePoint(Ball ball, MyPoint point) {
        Vector vector = new Vector(point, ball.getPosition());
        vector.setLength(ball.getRadius());
        MyPoint resPoint = vector.getEndPointVector(point);
        ball.setPosition(resPoint);
    }

    private void normalizeLine(Ball ball) {
        MyPoint collisionPoint = wall.getLine().getProjectionPointToLine(ball.getPosition());
        normalizePoint(ball, collisionPoint);
    }

    private boolean isBetween(MyPoint point) {
        double leftCoefficient = wall.getLeftParallelLine().getSignEquationLine(point);
        double rightCoefficient = wall.getRightParallelLine().getSignEquationLine(point);
        return leftCoefficient * rightCoefficient <= 0;

    }

    @Override
    public boolean detect() {
        if (GeometricalCalculation.getDistanceBetweenTwoPoint(wall.getStart(), ball.getPosition()) <= ball.getRadius()
                && (wall.getLine().getSignEquationLine(wall.getStart()) >= 0)) {
            normalizePoint(ball, wall.getStart());
            return true;
        }
        if (GeometricalCalculation.getDistanceBetweenTwoPoint(wall.getEnd(), ball.getPosition()) <= ball.getRadius()
                && (wall.getLine().getSignEquationLine(wall.getEnd()) >= 0)) {
            normalizePoint(ball, wall.getEnd());
            return true;
        }
        if ((wall.getLine().calculateDistanceToPoint(ball.getPosition()) <= ball.getRadius())
                && (isBetween(ball.getPosition()))) {
            normalizeLine(ball);
            return true;
        }
        return false;
    }

    @Override
    public void collision() {
        ball.sumPerpendicularVector(wall.getLine().getNormal());
    }
}
