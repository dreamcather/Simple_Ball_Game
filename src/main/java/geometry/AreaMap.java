package geometry;

import java.util.ArrayList;

public class AreaMap {
    private final ArrayList<MyPolygon> myPolygons;

    public AreaMap() {
        myPolygons = new ArrayList<>();
    }

    public void add(MyPolygon polygon) {
        myPolygons.add(polygon);
    }

    public boolean isBelong(MyPoint point) {
        boolean flag = false;
        for (MyPolygon myPolygon : myPolygons) {
            if (myPolygon.isBelong(point))
                flag = true;
        }
        return flag;
    }
}
