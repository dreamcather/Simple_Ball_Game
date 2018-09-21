package object;

import interaction.BallVisitor;

public class Enemy extends Ball {

    public Enemy(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius);
    }

    @Override
    public <T> T collisionReaction(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
