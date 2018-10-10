package visual;

import gameObject.ClosedWall;
import geometry.MyPoint;

public class ClosedWallVisitor implements Visible {
    private ClosedWall closedWall;

    public ClosedWallVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        MyPoint[] points = closedWall.getPoints();
        MyPoint[] res = new MyPoint[points.length];
        for(int i = 0;i<res.length;i++){
            MyPoint current = camera.transformPoint(points[i]);
            current.setX(current.getX()+camera.getXOffset());
            current.setY(current.getY()+camera.getXOffset());
            res[i] = current;
        }
        return new ClosedWallVisualInformation(res);
    }
}
