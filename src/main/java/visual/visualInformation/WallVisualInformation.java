package visual.visualInformation;

import gameObject.GameObject;
import geometry.MyPoint;

public class WallVisualInformation extends VisualInformation {
    private final MyPoint start;
    private final MyPoint end;

    public WallVisualInformation(MyPoint start, MyPoint end, GameObject gameObject) {
        super(gameObject);
        this.start = start;
        this.end = end;
    }

    public MyPoint getStart() {
        return start;
    }

    public MyPoint getEnd() {
        return end;
    }
}
