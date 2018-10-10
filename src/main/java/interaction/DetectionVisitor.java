package interaction;

import gameObject.*;
import visual.Camera;
import visual.visualInformation.VisualInformation;

public class DetectionVisitor implements ObjectInteractionVisitor<ObjectInteractionVisitor<Interaction>> {

    @Override
    public ObjectInteractionVisitor<Interaction> visit(Enemy enemy) {
        return new EnemyDetectionVisitor(enemy);
    }

    @Override
    public ObjectInteractionVisitor<Interaction> visit(Player player) {
        return new PlayerInteractionVisitor(player);
    }

    @Override
    public ObjectInteractionVisitor<Interaction> visit(Prize prize) {
        return new PrizeInteractionVisitor(prize);
    }

    @Override
    public ObjectInteractionVisitor<Interaction> visit(Wall wall) {
        return new WallInteractionVisitor(wall);
    }

    @Override
    public ObjectInteractionVisitor<Interaction> visit(ClosedWall closedWall) {
        return new ClosedWallInteractionVisitor(closedWall);
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return null;
    }
}
