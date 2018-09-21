package interaction;

import object.Enemy;
import object.Hero;
import object.Prize;

public class CollisionVisitor implements BallVisitor<BallVisitor<Collision>> {
    @Override
    public BallVisitor<Collision> visit(Enemy enemy) {
        return new EnemyVisitor(enemy);
    }

    @Override
    public BallVisitor<Collision> visit(Hero hero) {
        return new HeroVisitor(hero);
    }

    @Override
    public BallVisitor<Collision> visit(Prize prize) {
        return new PrizeVisitor(prize);
    }
}
