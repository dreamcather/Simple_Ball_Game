package visual.visualInformation;

import geometry.MyPoint;

public class EnemyVisualInformation extends BallVisualInformation {
    static private String type = "E";

    public EnemyVisualInformation(String visibleType, MyPoint point) {
        super(type, point);
    }
}
