package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import geometry.Point;


public class VisualInformation {
    public Point ballPoint;
    public Point wallStart;
    public Point wallEnd;

    public VisualInformation(GameObject gameObject,Camera camera) {
        if(gameObject.type!="W") {
            ballPoint = new Point(0,0);
            ballPoint.setX(gameObject.getPosition().getX()-camera.getPosition().getX()+250+camera.getxOfsset());
            ballPoint.setY(gameObject.getPosition().getY()-camera.getPosition().getY()+250+camera.getxOfsset());
        }
        else {
            Wall wall =(Wall) gameObject;
            Wall transforWall = new Wall(camera.transformPoint(wall.getStart()),
                    camera.transformPoint(wall.getEnd()));
            Point start = transforWall.getStart();
            Point end = transforWall.getEnd();
            if(camera.isVisible(wall.getStart()))
                start = transforWall.getStart();
            else{
                start = camera.getPoint(transforWall.getStart(),transforWall);
            }
            if(camera.isVisible(wall.getEnd()))
                end = transforWall.getEnd();
            else{
                end = camera.getPoint(transforWall.getEnd(),transforWall);
            }
            start.setX(start.getX()+camera.getxOfsset());
            start.setY(start.getY()+camera.getxOfsset());
            end.setX(end.getX()+camera.getxOfsset());
            end.setY(end.getY()+camera.getxOfsset());
            wallStart = start;
            wallEnd = end;
        }
    }
}
