package interaction;

import gameObject.Ball;
import gameObject.ClosedWall;
import geometry.GeometricalCalculation;
import geometry.MyPoint;

public class RegularBallAndClosedWallInteraction implements Interaction {
    private ClosedWall closedWall;
    private Ball ball;

    public RegularBallAndClosedWallInteraction(ClosedWall closedWall, Ball ball) {
        this.closedWall = closedWall;
        this.ball = ball;
    }

    @Override
    public boolean detect() {
        double distanceToClosedWall = closedWall.getDistance(ball.getPosition());
        return distanceToClosedWall <= ball.getRadius();
    }

    @Override
    public void collision() {
        ball.sumPerpendicularVector(closedWall.getResultPerpendicularVector(ball));
        MyPoint collisionPoint = GeometricalCalculation.getNearestPointOfPolygon(closedWall.getPolygon(),
                                                                                 ball.getPosition());
        MyPoint correctPoint = GeometricalCalculation.pushingPointAway(collisionPoint,
                                                                       ball.getPosition(),
                                                                       ball.getRadius());
        ball.setPosition(correctPoint);
    }
}
