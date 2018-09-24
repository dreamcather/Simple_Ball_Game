package interaction;

import object.Enemy;
import object.Player;
import object.Prize;
import object.Wall;

public class HeroVisitor implements ObjectInteractVisitor<Collision> {
    private Player hero;

    public HeroVisitor(Player hero) {
        this.hero = hero;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new DestroyCollision(hero, enemy);
    }

    @Override
    public Collision visit(Player hero) {
        return null;
    }

    @Override
    public Collision visit(Prize prize) {
        return new DestroyCollision(hero, prize);
    }

    @Override
    public Collision visit(Wall wall) {
        return new WallCollision(wall,hero);
    }
}
