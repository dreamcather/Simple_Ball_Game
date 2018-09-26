package gameObject;

import detection.ObjectDetectVisitor;
import geometry.Point;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;

public abstract class GameObject {

    public String type;

    public abstract <T> T collisionReaction(ObjectInteractVisitor<T> ballVisitor);

    public abstract  <T> T collisionDetection(ObjectDetectVisitor<T> objectDetectVisitor);

    public abstract void changeVector();

    public abstract void move(MotionControl motionControl);

    public abstract boolean isAlive();

    public abstract Point getPosition();

    public abstract String toString();
}
