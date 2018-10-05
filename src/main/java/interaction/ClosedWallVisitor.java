package interaction;

import gameObject.*;

public class ClosedWallVisitor implements ObjectInteractVisitor<Collision> {
    ClosedWall closedWall;

    public ClosedWallVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new ClosedWallCollision(closedWall,enemy);
    }

    @Override
    public Collision visit(Player player) {
        return new ClosedWallCollision(closedWall,player);
    }

    @Override
    public Collision visit(Prize prize) {
        return new ClosedWallCollision(closedWall,prize);
    }

    @Override
    public Collision visit(Wall wall) {
        return null;
    }

    @Override
    public Collision visit(ClosedWall closedWall) {
        return null;
    }
}
