package gameObject;

import interaction.ObjectInteractionVisitor;

public class Prize extends Ball {

    public Prize(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius,int key) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius,key);
        type = "Pr";
    }

    @Override
    public <T> T collision(ObjectInteractionVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

}
