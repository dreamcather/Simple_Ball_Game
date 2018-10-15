package interaction;

import gameObject.*;
import geometry.MyPoint;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import visual.Camera;
import visual.visualInformation.ClosedWallVisualInformation;
import visual.visualInformation.VisualInformation;

public class ClosedWallInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    ClosedWall closedWall;

    public ClosedWallInteractionVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularBallAndClosedWallInteraction(closedWall, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularBallAndClosedWallInteraction(closedWall, player);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularBallAndClosedWallInteraction(closedWall, prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new EmptyInteraction();
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new EmptyInteraction();
    }
}
