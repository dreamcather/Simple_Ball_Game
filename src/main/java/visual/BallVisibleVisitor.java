package visual;

import gameObject.Ball;

public class BallVisibleVisitor implements Visible {
    Ball ball;

    public BallVisibleVisitor(Ball ball) {
        this.ball = ball;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        if(camera.isVisible(ball.getPosition()))
        {
            VisualInformation visualInformation = new
                    VisualInformation("E");
            visualInformation.ballPoint = camera.transformPoint(ball.getPosition());
            return visualInformation;
        }
        return null;
    }
}
