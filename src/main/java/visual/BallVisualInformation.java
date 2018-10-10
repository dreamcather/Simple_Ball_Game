package visual;

import geometry.MyPoint;

public class BallVisualInformation extends VisualInformation {
    boolean visible;
    boolean tracked;
    MyPoint position;

    public BallVisualInformation(String type, String visibleType, MyPoint point) {
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

    public MyPoint getPosition() {
        return position;
    }
}
