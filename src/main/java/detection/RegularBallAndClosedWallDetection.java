package detection;

import gameObject.Ball;
import gameObject.ClosedWall;

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
        if(distanceToClosedWall<=ball.getRadius())
            return true;
        return false;
    }
}
