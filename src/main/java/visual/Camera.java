package visual;

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

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
