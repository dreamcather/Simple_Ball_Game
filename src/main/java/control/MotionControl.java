package control;

import geometry.MyPoint;
import javafx.scene.input.MouseEvent;
import visual.Camera;

import java.io.Serializable;

public class MotionControl implements Serializable {
    private MyPoint position;

    public MotionControl() {
        position = null;
    }

    public MotionControl(MouseEvent event, Camera camera) {
        position = new MyPoint(event.getSceneX() + camera.getPosition().getX() - 250 - camera.getOffset(),
                               event.getSceneY() + camera.getPosition().getY() - 250 - camera.getOffset());
    }

    public MyPoint getPosition() {
        return position;
    }

    public void setPosition(MyPoint position) {
        this.position = position;
    }
}
