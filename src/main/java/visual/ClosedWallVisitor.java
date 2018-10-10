package visual;

import gameObject.ClosedWall;
import geometry.MyPoint;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

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
        Coordinate[] coordinates = new Coordinate[res.length];
        for(int i=0;i<res.length;i++){
            coordinates[i] = res[i].convertPoint().getCoordinate();
        }
        Geometry geometry = new ConvexHull(coordinates,new GeometryFactory()).getConvexHull();
        Geometry camerGeometry = camera.convexHull.getConvexHull();
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
