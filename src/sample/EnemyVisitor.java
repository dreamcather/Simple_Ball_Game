package sample;

public class EnemyVisitor implements BallVisitor<Collision> {
    Enemy enemy;
    EnemyVisitor (Enemy _enemy){
        enemy =_enemy;
    }
    @Override
    public Collision visit(Enemy _enemy) {
        return new RegularCollision(_enemy,enemy);
    }

    @Override
    public Collision visit(Hero hero) {
        return null;
    }

    @Override
    public Collision visit(Point point) {
        return null;
    }
}
