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
        return null;
    }
}
