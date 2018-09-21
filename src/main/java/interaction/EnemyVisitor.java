package interaction;

import object.Enemy;
import object.Hero;
import object.Prize;

public class EnemyVisitor implements BallVisitor<Collision> {
    private Enemy enemy;

    EnemyVisitor(Enemy _enemy) {
        enemy = _enemy;
    }

    @Override
    public Collision visit(Enemy _enemy) {
        return new RegularCollision(_enemy, enemy);
    }

    @Override
    public Collision visit(Hero hero) {
        return new RegularCollision(enemy, hero);
    }

    @Override
    public Collision visit(Prize point) {
        return new RegularCollision(point, enemy);
    }
}
