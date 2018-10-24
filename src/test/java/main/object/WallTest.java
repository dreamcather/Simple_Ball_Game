package main.object;

import gameObject.Wall;
import geometry.MyPoint;
import org.testng.Assert;
import org.testng.annotations.Test;
import visual.Camera;
import visual.visualInformation.VisualInformation;
import visual.visualInformation.WallVisualInformation;

public class WallTest {
    @Test
    public void isVisibleTest1() {
        Wall wall = new Wall("0 0 750 0", 0);
        Camera camera = new Camera(new MyPoint(200, 200), 0, 0, 1000, 1000, 1000);

        VisualInformation visualInformation = wall.isVisible(camera);
        MyPoint start = new MyPoint(50, 50);
        MyPoint end = new MyPoint(500, 50);
        MyPoint actualStart = ((WallVisualInformation) visualInformation).getStart();
        MyPoint actualEnd = ((WallVisualInformation) visualInformation).getEnd();

        boolean flag =true;

        if(!actualStart.equals(start))
            flag = false;

        if(!actualEnd.equals(end))
            flag = false;

        Assert.assertTrue(flag);

    }
}
