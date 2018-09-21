package detection;

import object.Wall;
import object.Ball;

public class BallDetectionVisitor implements ObjectDetectVisitor<Detection> {
    Ball ball;

    public BallDetectionVisitor(Ball ball) {
        this.ball = ball;
    }

    @Override
    public Detection visit(Ball ball) {
        return new RegularTwoBallDetection(this.ball,ball);
    }

    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(ball,wall);
    }
}
