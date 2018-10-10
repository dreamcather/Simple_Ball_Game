package detection;

import gameObject.*;
import geometry.MyPoint;
import interaction.ObjectInteractVisitor;
import visual.Camera;
import visual.VisualInformation;
import visual.WallVisualInformation;

public class WallDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Wall wall;

    public WallDetectionVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularBallAndWallDetection(enemy,wall);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularBallAndWallDetection(player,wall);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularBallAndWallDetection(prize,wall);
    }

    @Override
    public Detection visit(Wall wall) {
        return new EmptyDetection();
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new EmptyDetection();
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
