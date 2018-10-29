package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class PlayerVisualInformation extends BallVisualInformation {
    private boolean clossed;
    public PlayerVisualInformation(MyPoint point, int radius,boolean clossed, GameObject gameObject) {
        super(point, radius, gameObject);
        this.clossed =clossed;
    }

    public boolean isClossed() {
        return clossed;
    }
}
