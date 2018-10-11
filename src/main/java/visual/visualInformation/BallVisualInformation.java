package visual.visualInformation;

import geometry.MyPoint;

public class BallVisualInformation extends VisualInformation {
    private MyPoint position;

    public BallVisualInformation(String type, MyPoint point) {
        super(type);
        position = point;
    }

    public MyPoint getPosition() {
        return position;
    }
}
