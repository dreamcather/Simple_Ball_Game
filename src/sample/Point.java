package sample;

public class Point extends Ball {
    Point(double _x, double _y, double _speed, GameState _game) {
        super(_x, _y, _speed, _game);
    }

    @Override
    public Boolean CollisionWithWall() {
        return null;
    }

    @Override
    public Boolean CollisionWithHero() {
        return null;
    }

    @Override
    public Boolean CollisionWithPoint() {
        return null;
    }

    @Override
    public Boolean CollisionWithEnemy() {
        return null;
    }
}
