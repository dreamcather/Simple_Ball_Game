package visual;

import gameObject.Wall;
import geometry.Point;

public class WallVisibleVisitor implements Visible {
    Wall wall;

    public WallVisibleVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public boolean isVisible(Camera camera) {
        Wall transforWall = new Wall(camera.transformPoint(wall.getStart()),
                camera.transformPoint(wall.getEnd()));
        Point start = transforWall.getStart();
        Point end = transforWall.getEnd();
        if(camera.isVisible(wall.getStart()))
            start = transforWall.getStart();
        else{
            start = camera.getPoint(transforWall.getStart(),transforWall);
        }
        if(camera.isVisible(wall.getEnd()))
            end = transforWall.getEnd();
        else{
            end = camera.getPoint(transforWall.getEnd(),transforWall);
        }
        if(start==null)
            return false;
        if(end == null)
            return false;
        return true;
    }
}
