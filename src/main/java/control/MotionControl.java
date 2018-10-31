package control;

import java.io.Serializable;

import geometry.MyPoint;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import visual.Camera;

public class MotionControl implements Serializable {
    private MyPoint position;
    private KeyCode key;

    public MotionControl() {
        position = null;
    }

    public MotionControl(KeyEvent event) {
        this.key = event.getCode();
    }

    public MotionControl(MouseEvent event, Camera camera) {
        position = new MyPoint(event.getSceneX() + camera.getPosition().getX() - camera.getSize() - camera.getOffset(),
                               event.getSceneY() + camera.getPosition().getY() - camera.getSize() - camera.getOffset());
    }

    public MyPoint getPosition() {
        return position;
    }

    public KeyCode getKey() {
        return key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

    public void setPosition(MyPoint position) {
        this.position = position;
    }
}
