package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
import geometry.Point;

public class Camera {
    Point position;
    int weight = 250;
    Wall upHorizontal;
    Wall downHorzontal;
    Wall leftVertical;
    Wall rightVertical;

    public Camera(Point position) {
        this.position = position;
        upHorizontal = new Wall(new Point(0, 2 * weight), new Point(0, 0));
        downHorzontal = new Wall(new Point(0, 2 * weight), new Point(2 * weight, 2 * weight));
        leftVertical = new Wall(new Point(0, 0), new Point(0, 2 * weight));
        rightVertical = new Wall(new Point(2 * weight, 2 * weight), new Point(2 * weight, 0));
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

    public boolean isVisible(GameObject gameObject){
        if(gameObject.type=="W"){
            return true;
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
