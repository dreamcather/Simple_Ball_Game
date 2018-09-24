package detection;

import object.*;

public class EnemyDetectionVisitor implements ObjectDetectVisitor<Detection> {
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
}
