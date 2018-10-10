package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;
import visual.BallVisibleVisitor;
import visual.Camera;
import visual.VisualInformation;

public class PlayerDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Player player;

    public PlayerDetectionVisitor(Player player) {
        this.player = player;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularTwoBallDetection(player, enemy);
    }

    @Override
    public Detection visit(Player player) {
        return null;
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularTwoBallDetection(player, prize);
    }

    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(player, wall);
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallDetection(closedWall, player);
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return new BallVisibleVisitor(player).isVisible(camera);
    }
}
