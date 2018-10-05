package visual;

import gameObject.Ball;
import gameObject.Wall;
import geometry.Point;

public class BallVisibleVisitor implements Visible {
    Ball ball;

    public BallVisibleVisitor(Ball ball) {
        this.ball = ball;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        if (camera.isVisible(ball.getPosition())) {
            Point position = camera.transformPoint(ball.getPosition());
            position.setX(position.getX() + camera.getXOffset());
            position.setY(position.getY() + camera.getXOffset());
            VisualInformation visualInformation = new BallVisualInformation(ball.type, "V", position);
            return visualInformation;
        } else {
            Wall wall = new Wall(camera.transformPoint(ball.getPosition()), camera.transformPoint(camera.position));
            Point res = camera.getPoint(camera.transformPoint(ball.getPosition()), wall);
            res.setX(res.getX() + camera.getXOffset());
            res.setY(res.getY() + camera.getXOffset());
            VisualInformation visualInformation = new BallVisualInformation(ball.type, "T", res);
            return visualInformation;
        }
    }
}
