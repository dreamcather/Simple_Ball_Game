package interaction;

import object.Enemy;
import object.Player;
import object.Prize;
import object.Wall;

public class EnemyVisitor implements ObjectInteractVisitor<Collision> {
    private Enemy enemy;

    EnemyVisitor(Enemy _enemy) {
        enemy = _enemy;
    }

    @Override
    public Collision visit(Enemy _enemy) {
        return new RegularCollision(_enemy, enemy);
    }

    @Override
    public Collision visit(Player hero) {
        return new DestroyCollision(hero, enemy);
    }

    @Override
    public Collision visit(Prize point) {
        return new RegularCollision(point, enemy);
    }

    @Override
    public Collision visit(Wall wall) {
        return null;
    }
}
