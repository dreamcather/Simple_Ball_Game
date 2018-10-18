package gameObject;

import geometry.MyPoint;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.io.Serializable;
import java.util.Objects;

public abstract class GameObject implements Serializable {

    public String type;

    private int key;

    public GameObject(int key) {
        this.key = key;
    }

    public abstract <T> T collision(ObjectInteractionVisitor<T> ballVisitor);

    public abstract void changeVector();

    public abstract void move(MotionControl motionControl);

    public abstract boolean isAlive();

    public abstract MyPoint getPosition();

    public abstract String toString();

    public abstract VisualInformation isVisible(Camera camera);

    @Override
    public int hashCode() {
        return key;
    }
}
