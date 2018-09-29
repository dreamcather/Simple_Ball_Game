package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import geometry.Line;
import geometry.Point;

import java.awt.*;

public class Camera {
    Point position;
    int weight = 250;
    Wall upHorizontal;
    Wall downHorzontal;
    Wall leftVertical;
    Wall rightVertical;
    Point leftUpPoint;
    Point leftDownPoint;
    Point rightUpPoint;
    Point rightDownPoint;

    public Camera(Point position) {
        this.position = position;
        leftUpPoint = new Point(0,0);
        leftDownPoint = new Point(0,2*weight);
        rightUpPoint = new Point(2*weight,0);
        rightDownPoint = new Point(2*weight,2*weight);
        upHorizontal = new Wall(rightUpPoint,leftUpPoint);
        downHorzontal = new Wall(leftDownPoint,rightDownPoint);
        leftVertical = new Wall(leftUpPoint,leftDownPoint);
        rightVertical = new Wall(rightDownPoint,rightUpPoint);
    }

    public Point transformPoint(Point point) {
        return new Point(point.getX() - position.getX() + weight,
                point.getY() - position.getY() + weight);
    }

    public boolean isVisible(Point point){
        Point transformPoint = transformPoint(point);
        if((transformPoint.getY()<=2*weight)&&
                (transformPoint.getY()>=0)&&
                (transformPoint.getX()<=2*weight)&&
                (transformPoint.getX()>=0))
        {
            return true;
        }
        return false;
    }

    public Point getIntersection(Point point, Wall wall) {
        Point res = upHorizontal.getIntersectionPoint(wall);
        double min =100;
        Point minPoint = null;
        if (res != null) {
            min = res.getDistanceToPoint(point);
            minPoint =res;
        }
        res = downHorzontal.getIntersectionPoint(wall);
        if(res!=null)
        {
            if(res.getDistanceToPoint(point)<min){
                min = res.getDistanceToPoint(point);
                minPoint = res;
            }
        }
        res = leftVertical.getIntersectionPoint(wall);
        if(res!=null)
        {
            if(res.getDistanceToPoint(point)<min){
                min = res.getDistanceToPoint(point);
                minPoint = res;
            }
        }
        res = rightVertical.getIntersectionPoint(wall);
        if(res!=null)
        {
            if(res.getDistanceToPoint(point)<min){
                min = res.getDistanceToPoint(point);
                minPoint = res;
            }
        }
        return  minPoint;
    }

    public boolean isVisible(GameObject gameObject){
        if(gameObject.type=="W"){
            Wall wall = (Wall)gameObject;
            if(isVisible(wall.getStart())||isVisible(wall.getEnd()))
            return true;
            else{
                Wall transformWall = new Wall(transformPoint(wall.getStart()),
                        transformPoint(wall.getEnd()));
                if(getIntersection(transformPoint(wall.getStart()),transformWall)!=null)
                return true;
                else return false;
            }
        }
        else
        {
            Ball ball = (Ball)gameObject;
            if(isVisible(ball.getPosition())){
                return true;
            }
            return false;
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
