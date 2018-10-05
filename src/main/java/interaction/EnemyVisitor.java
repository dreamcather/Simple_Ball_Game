package interaction;

import gameObject.*;

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
        return new KillCollision(hero, enemy);
    }

    @Override
    public Collision visit(Prize point) {
        return new RegularCollision(point, enemy);
    }

    @Override
    public Collision visit(Wall wall) {
        return new WallCollision(wall,enemy);
    }

    @Override
    public Collision visit(ClosedWall closedWall) {
        return new ClosedWallCollision(closedWall,enemy);
    }
}
