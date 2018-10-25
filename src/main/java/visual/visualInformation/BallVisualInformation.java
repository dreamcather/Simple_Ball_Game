package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class BallVisualInformation extends VisualInformation {
    private MyPoint position;
    private int radius;

    public BallVisualInformation(MyPoint point,int radius, GameObject gameObject) {
        super(gameObject);
        position = point;
        this.radius=radius;
    }

    public MyPoint getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }
}
