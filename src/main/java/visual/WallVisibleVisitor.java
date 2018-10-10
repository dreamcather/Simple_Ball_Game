package visual;

import gameObject.Wall;
import geometry.MyPoint;

public class WallVisibleVisitor implements Visible {
    Wall wall;

    public WallVisibleVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        Wall transformWall = new Wall(camera.transformPoint(wall.getStart()),
                camera.transformPoint(wall.getEnd()));
        MyPoint start = null;
        MyPoint end = null;
        if (camera.isVisible(wall.getStart()))
            start = transformWall.getStart();
        else {
            start = camera.getPoint(transformWall.getStart(), transformWall.lineSegment);
        }
        if (camera.isVisible(wall.getEnd()))
            end = transformWall.getEnd();
        else {
            end = camera.getPoint(transformWall.getEnd(), transformWall.lineSegment);
        }
        if (start == null)
            return null;
        if (end == null)
            return null;
        start.setX(start.getX() + camera.getXOffset());
        start.setY(start.getY() + camera.getXOffset());
        end.setX(end.getX() + camera.getXOffset());
        end.setY(end.getY() + camera.getXOffset());
        VisualInformation visualInformation = new WallVisualInformation(start, end);
        return visualInformation;
    }
}
