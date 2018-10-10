package interaction;

import geometry.MyPoint;
import javafx.scene.input.MouseEvent;
import visual.Camera;


public class MotionControl {
    double rad;
    MyPoint position;

    public MotionControl() {
        rad =0;
        position =null;
    }
    public MotionControl(MouseEvent event, Camera camera){
        position = new MyPoint(event.getSceneX()+camera.getPosition().getX()-250-camera.getXOffset(),
                event.getSceneY()+camera.getPosition().getY()-250-camera.getXOffset());
    }

    public MyPoint getPosition() {
        return position;
    }

    public void setPosition(MyPoint position) {
        this.position = position;
    }
}
