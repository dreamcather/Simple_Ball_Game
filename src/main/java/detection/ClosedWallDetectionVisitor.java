package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;

public class ClosedWallDetectionVisitor implements ObjectInteractVisitor<Detection> {
    ClosedWall closedWall;

    public ClosedWallDetectionVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularBallAndClosedWallDetection(closedWall,enemy);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularBallAndClosedWallDetection(closedWall,player);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularBallAndClosedWallDetection(closedWall,prize);
    }

    @Override
    public Detection visit(Wall wall) {
        return new EmptyDetection();
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new EmptyDetection();
    }
}
