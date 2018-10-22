package interaction;

import gameObject.*;

public class ClosedWallInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    private ClosedWall closedWall;

    public ClosedWallInteractionVisitor(ClosedWall closedWall) {
        this.closedWall = closedWall;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularBallAndClosedWallInteraction(closedWall, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularBallAndClosedWallInteraction(closedWall, player);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularBallAndClosedWallInteraction(closedWall, prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new EmptyInteraction();
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new EmptyInteraction();
    }
}
