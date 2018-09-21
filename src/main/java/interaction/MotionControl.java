package interaction;

import geometry.Point;
import javafx.scene.input.MouseEvent;


public class MotionControl {
    double rad;
    Point position;

    public MotionControl() {
        rad =0;
        position =null;
    }
    public MotionControl(MouseEvent event){
      position = new Point(event.getSceneX(),event.getSceneY());
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
