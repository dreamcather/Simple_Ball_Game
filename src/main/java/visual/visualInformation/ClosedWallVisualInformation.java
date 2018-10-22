package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class ClosedWallVisualInformation extends VisualInformation {
    private MyPoint[] points;

    public ClosedWallVisualInformation(MyPoint[] points, GameObject gameObject) {
        super(gameObject);
        this.points = points;
    }

    public MyPoint[] getPoints() {
        return points;
    }
}
