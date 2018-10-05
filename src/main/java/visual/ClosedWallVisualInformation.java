package visual;

import geometry.Point;

public class ClosedWallVisualInformation extends VisualInformation {
    Point[] points;
    public ClosedWallVisualInformation(Point[] points) {
        super("CW");
        this.points = points;
    }
}
