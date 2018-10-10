package detection;

import gameObject.*;
import geometry.MyPoint;
import interaction.ObjectInteractVisitor;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import visual.Camera;
import visual.ClosedWallVisualInformation;
import visual.VisualInformation;

public class ClosedWallDetectionVisitor implements ObjectInteractVisitor<Detection> {
    ClosedWall closedWall;

    public ClosedWallDetectionVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularBallAndClosedWallDetection(closedWall,enemy);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularBallAndClosedWallDetection(closedWall,player);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularBallAndClosedWallDetection(closedWall,prize);
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
        MyPoint[] points = closedWall.getPoints();
        MyPoint[] res = new MyPoint[points.length];
        for(int i = 0;i<res.length;i++){
            MyPoint current = camera.transformPoint(points[i]);
            current.setX(current.getX()+camera.getXOffset());
            current.setY(current.getY()+camera.getXOffset());
            res[i] = current;
        }
        Coordinate[] coordinates = new Coordinate[res.length];
        for(int i=0;i<res.length;i++){
            coordinates[i] = res[i].convertPoint().getCoordinate();
        }
        Geometry geometry = new ConvexHull(coordinates,new GeometryFactory()).getConvexHull();
        Geometry camerGeometry = camera.getConvexHull().getConvexHull();
        Geometry resGeometry = geometry.intersection(camerGeometry);
        coordinates = resGeometry.getCoordinates();
        if(coordinates.length<3)
            return null;
        MyPoint[] resPointArray = new MyPoint[coordinates.length];
        for(int i=0;i<coordinates.length;i++){
            resPointArray[i] = new MyPoint(coordinates[i]);
        }
        return new ClosedWallVisualInformation(resPointArray);
    }
}
