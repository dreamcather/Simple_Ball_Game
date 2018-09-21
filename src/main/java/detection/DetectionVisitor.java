package detection;

import object.Wall;
import object.Ball;

public  class DetectionVisitor implements ObjectDetectVisitor<ObjectDetectVisitor<Detection>> {
    @Override
    public ObjectDetectVisitor<Detection> visit(Ball ball) {
        return new BallDetectionVisitor(ball);
    }
    @Override
    public ObjectDetectVisitor<Detection> visit(Wall wall) {
        return null;
    }
}
