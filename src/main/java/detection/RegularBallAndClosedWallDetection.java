package detection;

import gameObject.Ball;
import gameObject.ClosedWall;
import geometry.GeometricalCalculation;
import geometry.MyPoint;

public class RegularBallAndClosedWallDetection implements Detection {
    ClosedWall closedWall;
    Ball ball;

    public RegularBallAndClosedWallDetection(ClosedWall closedWall, Ball ball) {
        this.closedWall = closedWall;
        this.ball = ball;
    }

    @Override
    public boolean detect() {
        double distanceToClosedWall = closedWall.getDistance(ball.getPosition());
        if(distanceToClosedWall<=ball.getRadius()) {
            return true;
        }
        return false;
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
