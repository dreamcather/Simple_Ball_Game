package visual;

import gameObject.Wall;
import geometry.Point;

public class WallVisibleVisitor implements Visible {
    Wall wall;

    public WallVisibleVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        Wall transformWall = new Wall(camera.transformPoint(wall.getStart()),
                camera.transformPoint(wall.getEnd()));
        Point start = null;
        Point end = null;
        if(camera.isVisible(wall.getStart()))
            start = transformWall.getStart();
        else{
            start = camera.getPoint(transformWall.getStart(),transformWall);
        }
        if(camera.isVisible(wall.getEnd()))
            end = transformWall.getEnd();
        else{
            end = camera.getPoint(transformWall.getEnd(),transformWall);
        }
        if(start==null)
            return null;
        if(end == null)
            return null;
        VisualInformation visualInformation = new VisualInformation("W");
        visualInformation.wallStart = start;
        visualInformation.wallEnd = end;
        visualInformation.wallStart.setX(start.getX()+camera.getxOfsset());
        visualInformation.wallStart.setY(start.getY()+camera.getxOfsset());
        visualInformation.wallEnd.setX(end.getX()+camera.getxOfsset());
        visualInformation.wallEnd.setY(end.getY()+camera.getxOfsset());
        return visualInformation;
    }
}
