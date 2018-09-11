package sample;

public interface BallVisitor<T> {
    T visit(Enemy enemy);
    T visit(Hero hero);
    T visit(Prize prize);
}
