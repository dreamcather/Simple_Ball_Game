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
            visualInformation.ballPoint.setX(camera.transformPoint(ball.getPosition()).getX()+camera.getxOfsset());
            visualInformation.ballPoint.setY(camera.transformPoint(ball.getPosition()).getY()+camera.getxOfsset());
            return visualInformation;
        }
        return null;
    }
}
