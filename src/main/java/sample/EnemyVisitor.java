package sample;

public class EnemyVisitor implements BallVisitor<Collision> {
    Enemy enemy;

    EnemyVisitor(Enemy _enemy) {
        enemy = _enemy;
    }

    @Override
    public Collision visit(Enemy _enemy) {
        return new RegularCollision(_enemy.ball, enemy.ball);
    }

    @Override
    public Collision visit(Hero hero) {
        return new RegularCollision(enemy.ball, hero.ball);
    }

    @Override
    public Collision visit(Prize point) {
        return new RegularCollision(point.ball, enemy.ball);
    }
}
