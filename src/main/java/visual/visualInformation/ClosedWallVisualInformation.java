package visual.visualInformation;

import geometry.MyPoint;
import visual.visualInformation.VisualInformation;

public class ClosedWallVisualInformation extends VisualInformation {
    MyPoint[] points;
    public ClosedWallVisualInformation(MyPoint[] points) {
        super("CW");
        this.points = points;
    }

    public MyPoint[] getPoints() {
        return points;
    }
}
