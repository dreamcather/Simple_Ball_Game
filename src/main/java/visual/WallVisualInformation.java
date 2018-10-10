package visual;

import geometry.MyPoint;

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
