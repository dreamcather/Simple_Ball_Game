package gameObject;

import detection.ObjectDetectVisitor;
import interaction.ObjectInteractVisitor;

public class Prize extends Ball {

    public Prize(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius);
        type ="Pr";
    }

    @Override
    public <T> T collisionReaction(ObjectInteractVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public <T> T collisionDetection(ObjectDetectVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }
}