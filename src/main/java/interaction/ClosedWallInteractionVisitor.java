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

    @Override
    public VisualInformation isVisible(Camera camera) {
        MyPoint[] points = closedWall.getPoints();
        MyPoint[] res = new MyPoint[points.length];
        for (int i = 0; i < res.length; i++) {
            MyPoint current = camera.transformPoint(points[i]);
            current.setX(current.getX() + camera.getOffset());
            current.setY(current.getY() + camera.getOffset());
            res[i] = current;
        }
        Coordinate[] coordinates = new Coordinate[res.length];
        for (int i = 0; i < res.length; i++) {
            coordinates[i] = res[i].convertPoint().getCoordinate();
        }
        Geometry geometry = new ConvexHull(coordinates, new GeometryFactory()).getConvexHull();
        Geometry camerGeometry = camera.getConvexHull().getConvexHull();
        Geometry resGeometry = geometry.intersection(camerGeometry);
        coordinates = resGeometry.getCoordinates();
        if (coordinates.length < 3)
            return null;
        MyPoint[] resPointArray = new MyPoint[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            resPointArray[i] = new MyPoint(coordinates[i]);
        }
        return new ClosedWallVisualInformation(resPointArray);
    }
}
