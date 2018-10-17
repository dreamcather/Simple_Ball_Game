package gameObject;

import geometry.MyPoint;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

    public String type;

    public abstract <T> T collision(ObjectInteractionVisitor<T> ballVisitor);

    public abstract void changeVector();

    public abstract void move(MotionControl motionControl);

    public abstract boolean isAlive();

    public abstract MyPoint getPosition();

    public abstract String toString();

    public abstract VisualInformation isVisible(Camera camera);
}
