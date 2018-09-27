package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;

public class PlayerDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Player player;

    public PlayerDetectionVisitor(Player player) {
        this.player = player;
    }
    @Override
    public Detection visit(Enemy enemy) {
        return new RegularTwoBallDetection(player,enemy);
    }
    @Override
    public Detection visit(Player player) {
        return null;
    }
    @Override
    public Detection visit(Prize prize) {
        return new RegularTwoBallDetection(player,prize);
    }
    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(player,wall);
    }
}
