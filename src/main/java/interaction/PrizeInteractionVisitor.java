package interaction;

import gameObject.*;

public class PrizeInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    private final Prize prize;

    public PrizeInteractionVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularTwoBallInteraction(prize, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new DestroyCollision(player, prize);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new RegularTwoBallInteraction(this.prize, prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new RegularBallAndWallInteraction(prize, wall);
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallInteraction(closedWall, prize);
    }

}
