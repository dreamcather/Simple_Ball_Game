package detection;

import object.Wall;
import object.Ball;

public class WallDetectionVisitor implements ObjectDetectVisitor<Detection>  {
    Wall wall;

    public WallDetectionVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Detection visit(Ball ball) {
        return new RegularBallAndWallDetection(ball,wall);
    }

    @Override
    public Detection visit(Wall wall) {
        return null;
    }
}
