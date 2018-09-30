package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import geometry.GeometricalCalculation;
import geometry.Point;
import java.util.ArrayList;

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

    public Point getPoint(Point point, Wall wall){
        Point current;
        ArrayList<Point> list = new ArrayList<>();
        if((current=upHorizontal.getIntersectionPoint(wall))!=null)
            list.add(current);
        if((current=downHorzontal.getIntersectionPoint(wall))!=null)
            list.add(current);
        if((current=leftVertical.getIntersectionPoint(wall))!=null)
            list.add(current);
        if((current=rightVertical.getIntersectionPoint(wall))!=null)
            list.add(current);
        if(list.size()==0)
            return null;
        double distance = GeometricalCalculation.getDistanceBetweenTwoPoint(list.get(0),point);
        current = list.get(0);
        for(int i=1;i<list.size();i++){
            double curDistance = GeometricalCalculation.getDistanceBetweenTwoPoint(list.get(i),point);
            if(curDistance<distance){
                current = list.get(i);
                distance = curDistance;
            }
        }
        return current;
    }

    public boolean isVisible(GameObject gameObject){
        if(gameObject.type=="W"){
            Wall wall = (Wall)gameObject;
            if(isVisible(wall.getStart())||isVisible(wall.getEnd()))
            return true;
            else{
                Wall transformWall = new Wall(transformPoint(wall.getStart()),
                        transformPoint(wall.getEnd()));
                if((getPoint(transformWall.getStart(),transformWall)!=null)||
                        (getPoint(transformWall.getEnd(),transformWall)!=null))
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
        double x = position.getX();
        double y = position.getY();
        if(x<250)
            x=250;
        if(y<250)
            y=250;
        if(x>750)
            x=750;
        if(y>750)
            y=750;
        this.position.setX(x);
        this.position.setY(y);
    }
}
