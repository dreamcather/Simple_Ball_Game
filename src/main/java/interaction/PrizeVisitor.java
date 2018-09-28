package interaction;

import gameObject.Enemy;
import gameObject.Player;
import gameObject.Prize;
import gameObject.Wall;

public class PrizeVisitor implements ObjectInteractVisitor<Collision> {
    private Prize prize;

    public PrizeVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(enemy, prize);
    }

    @Override
    public Collision visit(Player hero) {
        return new DestroyCollision(hero, prize);
    }

    @Override
    public Collision visit(Prize prize) {
        return new RegularCollision(prize, this.prize);
    }

    @Override
    public Collision visit(Wall wall) {
        return new WallCollision(wall,prize);
    }
}
