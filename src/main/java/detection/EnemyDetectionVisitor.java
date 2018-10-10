package detection;

import gameObject.*;
import interaction.ObjectInteractVisitor;
import visual.BallVisibleVisitor;
import visual.Camera;
import visual.VisualInformation;

public class EnemyDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Enemy enemy;

    public EnemyDetectionVisitor(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularTwoBallDetection(this.enemy,enemy);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularTwoBallDetection(enemy,player);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularTwoBallDetection(enemy,prize);
    }

    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(enemy,wall);
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallDetection(closedWall,enemy);
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return new BallVisibleVisitor(enemy).isVisible(camera);

    }
}
