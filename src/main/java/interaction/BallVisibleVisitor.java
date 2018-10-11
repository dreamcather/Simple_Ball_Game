package interaction;

import gameObject.Ball;
import gameObject.Wall;
import geometry.MyPoint;
import visual.Camera;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.VisualInformation;

public class BallVisibleVisitor {
    private Ball ball;

    public BallVisibleVisitor(Ball ball) {
        this.ball = ball;
    }

    public VisualInformation isVisible(Camera camera) {
        if (camera.isVisible(ball.getPosition())) {
            MyPoint position = camera.transformPoint(ball.getPosition());
            position.setX(position.getX() + camera.getOffset());
            position.setY(position.getY() + camera.getOffset());
            return new BallVisualInformation(ball.type, position);
        } else {
            Wall wall = new Wall(camera.transformPoint(ball.getPosition()),
                                 camera.transformPoint(camera.getPosition()));
            MyPoint res = camera.getPoint(camera.transformPoint(ball.getPosition()), wall.lineSegment);
            if (res == null)
                return null;
            res.setX(res.getX() + camera.getOffset());
            res.setY(res.getY() + camera.getOffset());
            return new BallVisualInformation(ball.type, res);
        }
    }
}
