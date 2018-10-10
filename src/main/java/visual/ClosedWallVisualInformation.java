package visual;

import geometry.MyPoint;

public class ClosedWallVisualInformation extends VisualInformation {
    MyPoint[] points;
    public ClosedWallVisualInformation(MyPoint[] points) {
        super("CW");
        this.points = points;
    }
}
