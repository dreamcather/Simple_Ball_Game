package gameObject;

import interaction.ObjectInteractionVisitor;

public class Enemy extends Ball {

    public Enemy(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, int radius, int key) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius, key);
        type = "E";
    }

    @Override
    public <T> T collision(ObjectInteractionVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

}
