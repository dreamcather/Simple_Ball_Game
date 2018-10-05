package visual;

import geometry.Point;

public class BallVisualInformation extends VisualInformation {
    boolean visible;
    boolean tracked;
    Point position;

    public BallVisualInformation(String type, String visibleType, Point point) {
        super(type);
        if (visibleType == "V") {
            visible = true;
            tracked = false;
        } else {
            visible = false;
            tracked = true;
        }
        position = point;
    }

    public Point getPosition() {
        return position;
    }
}
