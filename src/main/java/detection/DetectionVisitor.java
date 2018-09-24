package detection;

import object.*;

public class DetectionVisitor implements ObjectDetectVisitor<ObjectDetectVisitor<Detection>> {

    @Override
    public ObjectDetectVisitor<Detection> visit(Enemy enemy) {
        return new EnemyDetectionVisitor(enemy);
    }

    @Override
    public ObjectDetectVisitor<Detection> visit(Player player) {
        return new PlayerDetectionVisitor(player);
    }

    @Override
    public ObjectDetectVisitor<Detection> visit(Prize prize) {
        return new PrizeDetectionVisitor(prize);
    }

    @Override
    public ObjectDetectVisitor<Detection> visit(Wall wall) {
        return new WallDetectionVisitor(wall);
    }
}
