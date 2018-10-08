package interaction;

import gameObject.Ball;
import gameObject.ClosedWall;
import geometry.GeometricalCalculation;
import geometry.Point;

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
        Point collisionPoint = GeometricalCalculation.getNearestPointOfPolygon(closedWall.getPolygon(),
                ball.getPosition());
        Point correctPoint = GeometricalCalculation.pushingPointAway(collisionPoint,
                ball.getPosition(),
                ball.getRadius());
        ball.setPosition(correctPoint);
    }
}
