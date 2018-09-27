package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;

public class DetectionVisitor implements ObjectInteractVisitor<ObjectInteractVisitor<Detection>> {

    @Override
    public ObjectInteractVisitor<Detection> visit(Enemy enemy) {
        return new EnemyDetectionVisitor(enemy);
    }

    @Override
    public ObjectInteractVisitor<Detection> visit(Player player) {
        return new PlayerDetectionVisitor(player);
    }

    @Override
    public ObjectInteractVisitor<Detection> visit(Prize prize) {
        return new PrizeDetectionVisitor(prize);
    }

    @Override
    public ObjectInteractVisitor<Detection> visit(Wall wall) {
        return new WallDetectionVisitor(wall);
    }
}
