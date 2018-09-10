package sample;

public class CollisionVisitor implements BallVisitor<BallVisitor<Collision>> {
    @Override
    public BallVisitor<Collision> visit(Enemy enemy) {
        return new EnemyVisitor(enemy);
    }

    @Override
    public BallVisitor<Collision> visit(Hero hero) {
        return null;
    }

    @Override
    public BallVisitor<Collision> visit(Point point) {
        return null;
    }
}
