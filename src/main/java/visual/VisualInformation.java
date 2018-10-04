package visual;

import geometry.Point;


public class VisualInformation {
    public String type;
    public Point ballPoint;
    public Point wallStart;
    public Point wallEnd;
    public VisualInformation(String type) {
        this.type = type;
    }
}
