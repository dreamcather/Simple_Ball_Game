package interaction;

import gameObject.*;

public class WallInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    private Wall wall;

    public WallInteractionVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularBallAndWallInteraction(enemy, wall);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularBallAndWallInteraction(player, wall);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularBallAndWallInteraction(prize, wall);
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
