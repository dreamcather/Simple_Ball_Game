package interaction;

import gameObject.*;

public class PlayerInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    private final Player player;

    public PlayerInteractionVisitor(Player player) {
        this.player = player;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new KillCollision(player, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return new RegularTwoBallInteraction(player, this.player);
    }

    @Override
    public Interaction visit(Prize prize) {
        return new DestroyCollision(player, prize);
    }

    @Override
    public Interaction visit(Wall wall) {
        return new RegularBallAndWallInteraction(player, wall);
    }

    @Override
    public Interaction visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallInteraction(closedWall, player);
    }

}
