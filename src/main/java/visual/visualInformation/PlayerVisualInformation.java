package visual.visualInformation;

import geometry.MyPoint;

public class PlayerVisualInformation extends BallVisualInformation {
    static private String type = "P";

    public PlayerVisualInformation(MyPoint point) {
        super(type, point);
    }
}
