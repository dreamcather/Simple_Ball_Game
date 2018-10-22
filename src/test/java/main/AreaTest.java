package main;

import geometry.AreaMap;
import geometry.MyPoint;
import geometry.MyPolygon;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AreaTest {
    @Test
    public void isBelongTest1() {
        AreaMap areaMap = new AreaMap();
        MyPolygon myPolygon = new MyPolygon(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(100, 100),
                new MyPoint(0, 100),
                new MyPoint(100, 0) });
        areaMap.add(myPolygon);
        MyPoint point = new MyPoint(50, 50);

        Assert.assertTrue(areaMap.isBelong(point));
    }

    @Test
    public void isBelongTest2() {
        AreaMap areaMap = new AreaMap();
        MyPolygon myPolygon = new MyPolygon(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(100, 100),
                new MyPoint(0, 100),
                new MyPoint(100, 0) });
        areaMap.add(myPolygon);
        MyPoint point = new MyPoint(150, 50);

        Assert.assertFalse(areaMap.isBelong(point));
    }

    @Test
    public void isBelongTest3() {
        AreaMap areaMap = new AreaMap();
        MyPolygon myPolygon = new MyPolygon(new MyPoint[] {
                new MyPoint(0, 0),
                new MyPoint(100, 100),
                new MyPoint(0, 100),
                new MyPoint(100, 0) });
        areaMap.add(myPolygon);
        MyPoint point = new MyPoint(100, 50);

        Assert.assertTrue(areaMap.isBelong(point));
    }
}
