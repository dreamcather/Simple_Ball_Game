package visual;

import gameObject.ClosedWall;
import geometry.GeometricalCalculation;
import geometry.Point;
import geometry.Polygon;

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
        Polygon polygon = new Polygon(res);
        polygon = GeometricalCalculation.narrowPolygon(polygon,camera.upHorizontal.getMainLine());
        polygon = GeometricalCalculation.narrowPolygon(polygon,camera.downHorizontal.getMainLine());
        polygon = GeometricalCalculation.narrowPolygon(polygon,camera.leftVertical.getMainLine());
        polygon = GeometricalCalculation.narrowPolygon(polygon,camera.rightVertical.getMainLine());
        res = polygon.getPoints();
        if(res.length==0){
            return null;
        }
        return new ClosedWallVisualInformation(res);
    }
}
