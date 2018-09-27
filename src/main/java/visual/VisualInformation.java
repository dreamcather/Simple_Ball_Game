package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import geometry.Point;


public class VisualInformation {
    public Point ballPoint;
    public Point wallStart;
    public Point wallEnd;

    public VisualInformation(GameObject gameObject) {
        if(gameObject.type!="W") {
            ballPoint = gameObject.getPosition();
        }
        else {
            Wall wall =(Wall) gameObject;
            wallStart = wall.getStart();
            wallEnd = wall.getEnd();
        }
    }
}
