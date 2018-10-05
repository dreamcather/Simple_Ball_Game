package visual;

import gameObject.ClosedWall;
import geometry.Point;

public class ClosedWallVisitor implements Visible {
    ClosedWall closedWall;

    public ClosedWallVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        Point[] points = closedWall.getPoints();
        Point[] res = new Point[points.length];
        for(int i = 0;i<res.length;i++){
            Point current = camera.transformPoint(points[i]);
            current.setX(current.getX()+camera.getXOffset());
            current.setY(current.getY()+camera.getXOffset());
            res[i] = current;
        }
        return new ClosedWallVisualInformation(res);
    }
}
