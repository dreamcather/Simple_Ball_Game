package visual;

import geometry.Point;

public class WallVisualInformation extends VisualInformation {
    Point start;
    Point end;

    public WallVisualInformation(Point start, Point end) {
        super("W");
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}
