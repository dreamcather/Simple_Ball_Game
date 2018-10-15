package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class BallVisualInformation extends VisualInformation {
    private MyPoint position;

    public BallVisualInformation(MyPoint point, GameObject gameObject) {
        super(gameObject);
        position = point;
    }

    public MyPoint getPosition() {
        return position;
    }
}
