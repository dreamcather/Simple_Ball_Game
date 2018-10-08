package main;

import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.Point;
import geometry.Polygon;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeometricalCalculationTest {
    @Test
    public void ProjectionPointOnLine1() {
        Line line = new Line(new Point(0, 0), new Point(10, 0));
        Point point = new Point(5, 5);

        Point actual = GeometricalCalculation.getProjectionPointToLine(line, point);
        Point expected = new Point(5, 0);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void ProjectionPointOnLine2() {
        Line line = new Line(new Point(10, 6), new Point(10, 8));
        Point point = new Point(20, 7);

        Point actual = GeometricalCalculation.getProjectionPointToLine(line, point);
        Point expected = new Point(10, 7);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void NearestPointOfPolygonTest1() {
        Polygon polygon = new Polygon(new Point[] {
                new Point(0, 0),
                new Point(0, 10),
                new Point(10, 10),
                new Point(10, 0) });
        Point point = new Point(15, 5);

        Point actual = GeometricalCalculation.getNearestPointOfPolygon(polygon, point);
        Point expected = new Point(10, 5);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void NearestPointOfPolygonTest2() {
        Polygon polygon = new Polygon(new Point[] {
                new Point(0, 0),
                new Point(0, 10),
                new Point(10, 10),
                new Point(10, 0) });
        Point point = new Point(15, 0);

        Point actual = GeometricalCalculation.getNearestPointOfPolygon(polygon, point);
        Point expected = new Point(10, 0);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void PushingPointAwayTest1() {
        Point controlPoint = new Point(0, 0);
        Point movePoint = new Point(5, 0);
        double distance = 10;

        Point actual = GeometricalCalculation.pushingPointAway(controlPoint, movePoint, distance);
        Point expected = new Point(10,0);

        Assert.assertTrue(actual.equals(expected));

    }

}
