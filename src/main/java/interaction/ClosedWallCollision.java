package interaction;

import gameObject.Ball;
import gameObject.ClosedWall;

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
    }
}
