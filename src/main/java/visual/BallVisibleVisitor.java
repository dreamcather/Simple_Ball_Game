package visual;

import gameObject.Ball;

public class BallVisibleVisitor implements Visible {
    Ball ball;

    public BallVisibleVisitor(Ball ball) {
        this.ball = ball;
    }

    @Override
    public boolean isVisible(Camera camera) {
        if(camera.isVisible(ball.getPosition()))
        return true;
        return false;
    }
}
