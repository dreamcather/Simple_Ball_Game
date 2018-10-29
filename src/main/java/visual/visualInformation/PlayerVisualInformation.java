package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class PlayerVisualInformation extends BallVisualInformation {
    private final boolean closed;
    public PlayerVisualInformation(MyPoint point, int radius, boolean closed, GameObject gameObject) {
        super(point, radius, gameObject);
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }
}
