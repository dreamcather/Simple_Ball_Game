package interaction;

import gameObject.Ball;
import gameObject.ClosedWall;
import geometry.GeometricalCalculation;
import geometry.MyPoint;

public class ClosedWallCollision extends Collision {
    ClosedWall closedWall;
    Ball ball;

    public ClosedWallCollision(ClosedWall closedWall, Ball ball) {
        this.closedWall = closedWall;
        this.ball = ball;
    }

    @Override
    public void collide() {
        ball.sumPerpendicularVector(closedWall.getResultPerpendicularVector(ball));
        MyPoint collisionPoint = GeometricalCalculation.getNearestPointOfPolygon(closedWall.getPolygon(),
                ball.getPosition());
        MyPoint correctPoint = GeometricalCalculation.pushingPointAway(collisionPoint,
                ball.getPosition(),
                ball.getRadius());
        ball.setPosition(correctPoint);
    }
}
