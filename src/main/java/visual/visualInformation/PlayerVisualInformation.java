package visual.visualInformation;

import geometry.MyPoint;

public class PlayerVisualInformation extends BallVisualInformation {
    static private String type = "P";
    public PlayerVisualInformation(String visibleType, MyPoint point) {
        super(type, visibleType, point);
    }
}
