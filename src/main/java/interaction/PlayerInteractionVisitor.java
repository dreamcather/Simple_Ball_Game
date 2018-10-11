package interaction;

import gameObject.*;
import visual.Camera;
import visual.visualInformation.VisualInformation;

public class PlayerInteractionVisitor implements ObjectInteractionVisitor<Interaction> {
    Player player;

    public PlayerInteractionVisitor(Player player) {
        this.player = player;
    }

    @Override
    public Interaction visit(Enemy enemy) {
        return new RegularTwoBallInteraction(player, enemy);
    }

    @Override
    public Interaction visit(Player player) {
        return null;
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

    @Override
    public VisualInformation isVisible(Camera camera) {
        return new BallVisibleVisitor(player).isVisible(camera);
    }
}
