package detection;

import gameObject.*;
import geometry.Vector;
import interaction.ObjectInteractVisitor;
import visual.BallVisibleVisitor;
import visual.Camera;
import visual.VisualInformation;

public class PrizeDetectionVisitor implements ObjectInteractVisitor<Detection> {
    Prize prize;

    public PrizeDetectionVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularTwoBallDetection(prize,enemy);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularTwoBallDetection(prize,player);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularTwoBallDetection(this.prize,prize);
    }

    @Override
    public Detection visit(Wall wall) {
        return new RegularBallAndWallDetection(prize,wall);
    }

    @Override
    public Detection visit(ClosedWall closedWall) {
        return new RegularBallAndClosedWallDetection(closedWall,prize);
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return new BallVisibleVisitor(prize).isVisible(camera);
    }
}
