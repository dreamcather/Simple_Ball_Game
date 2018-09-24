package interaction;

import detection.PlayerDetectionVisitor;
import object.Enemy;
import object.Player;
import object.Prize;
import object.Wall;

public class ObjectInteractionVisitor implements ObjectInteractVisitor<ObjectInteractVisitor<Collision>>{

    @Override
    public ObjectInteractVisitor<Collision> visit(Enemy enemy) {
        return new EnemyVisitor(enemy);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Player player) {
        return new HeroVisitor(player);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Prize prize) {
        return new PrizeVisitor(prize);
    }

    @Override
    public ObjectInteractVisitor<Collision> visit(Wall wall) {
        return new WallVisitor(wall);
    }
}