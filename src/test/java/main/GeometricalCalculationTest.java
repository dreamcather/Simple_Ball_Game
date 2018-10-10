package main;

import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.MyPoint;
import geometry.MyPolygon;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeometricalCalculationTest {
    @Test
    public void ProjectionPointOnLine1() {
        Line line = new Line(new MyPoint(0, 0), new MyPoint(10, 0));
        MyPoint point = new MyPoint(5, 5);

        MyPoint actual = GeometricalCalculation.getProjectionPointToLine(line, point);
        MyPoint expected = new MyPoint(5, 0);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void ProjectionPointOnLine2() {
        Line line = new Line(new MyPoint(10, 6), new MyPoint(10, 8));
        MyPoint point = new MyPoint(20, 7);

        MyPoint actual = GeometricalCalculation.getProjectionPointToLine(line, point);
        MyPoint expected = new MyPoint(10, 7);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void NearestPointOfPolygonTest1() {
        MyPolygon polygon = new MyPolygon(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(0, 10),
                new MyPoint(10, 10),
                new MyPoint(10, 0) });
        MyPoint point = new MyPoint(15, 5);

        MyPoint actual = GeometricalCalculation.getNearestPointOfPolygon(polygon, point);
        MyPoint expected = new MyPoint(10, 5);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void NearestPointOfPolygonTest2() {
        MyPolygon polygon = new MyPolygon(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(0, 10),
                new MyPoint(10, 10),
                new MyPoint(10, 0) });
        MyPoint point = new MyPoint(15, 0);

        MyPoint actual = GeometricalCalculation.getNearestPointOfPolygon(polygon, point);
        MyPoint expected = new MyPoint(10, 0);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void PushingPointAwayTest1() {
        MyPoint controlPoint = new MyPoint(0, 0);
        MyPoint movePoint = new MyPoint(5, 0);
        double distance = 10;

        MyPoint actual = GeometricalCalculation.pushingPointAway(controlPoint, movePoint, distance);
        MyPoint expected = new MyPoint(10,0);

        Assert.assertTrue(actual.equals(expected));

    }

}
