package interaction;

import geometry.Point;
import javafx.scene.input.MouseEvent;
import visual.Camera;


public class MotionControl {
    double rad;
    Point position;

    public MotionControl() {
        rad =0;
        position =null;
    }
    public MotionControl(MouseEvent event, Camera camera){
        position = new Point(event.getSceneX()+camera.getPosition().getX()-250,
                event.getSceneY()+camera.getPosition().getY()-250);
    }

    public double getRad() {
        return rad;
    }

    public void setRad(double rad) {
        this.rad = rad;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
