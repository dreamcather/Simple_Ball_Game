package gameObject;

import geometry.MyPoint;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public abstract class GameObject {

    public String type;

    public abstract <T> T collision(ObjectInteractVisitor<T> ballVisitor);

    public abstract void changeVector();

    public abstract void move(MotionControl motionControl);

    public abstract boolean isAlive();

    public abstract MyPoint getPosition();

    public abstract String toString();
}
