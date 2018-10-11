package interaction;

import gameObject.*;
import geometry.MyPoint;
import visual.Camera;
import visual.visualInformation.VisualInformation;
import visual.visualInformation.WallVisualInformation;

public class WallInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    private Wall wall;

    public WallInteractionVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularBallAndWallInteraction(enemy, wall);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularBallAndWallInteraction(player, wall);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularBallAndWallInteraction(prize, wall);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new EmptyInteraction();
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new EmptyInteraction();
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        Wall transformWall = new Wall(camera.transformPoint(wall.getStart()), camera.transformPoint(wall.getEnd()));
        MyPoint start;
        MyPoint end;
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
        start.setX(start.getX() + camera.getOffset());
        start.setY(start.getY() + camera.getOffset());
        end.setX(end.getX() + camera.getOffset());
        end.setY(end.getY() + camera.getOffset());
        VisualInformation visualInformation = new WallVisualInformation(start, end);
        return visualInformation;
    }
}
