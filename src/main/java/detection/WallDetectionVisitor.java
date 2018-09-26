package detection;

import gameObject.*;

public class WallDetectionVisitor implements ObjectDetectVisitor<Detection> {
    Wall wall;

    public WallDetectionVisitor(Wall wall) {
        this.wall = wall;
    }

    @Override
    public Detection visit(Enemy enemy) {
        return new RegularBallAndWallDetection(enemy,wall);
    }

    @Override
    public Detection visit(Player player) {
        return new RegularBallAndWallDetection(player,wall);
    }

    @Override
    public Detection visit(Prize prize) {
        return new RegularBallAndWallDetection(prize,wall);
    }

    @Override
    public Detection visit(Wall wall) {
        return new EmptyDetection();
    }
}
