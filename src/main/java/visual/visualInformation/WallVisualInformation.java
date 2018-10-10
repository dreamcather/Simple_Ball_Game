package visual.visualInformation;

import geometry.MyPoint;
import visual.visualInformation.VisualInformation;

public class WallVisualInformation extends VisualInformation {
    MyPoint start;
    MyPoint end;

    public WallVisualInformation(MyPoint start, MyPoint end) {
        super("W");
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
