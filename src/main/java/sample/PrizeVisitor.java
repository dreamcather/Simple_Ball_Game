package sample;

public class PrizeVisitor implements BallVisitor<Collision> {
    Prize prize;

    public PrizeVisitor(Prize prize) {
        this.prize = prize;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(enemy.ball, prize.ball);
    }

    @Override
    public Collision visit(Hero hero) {
        return new DestroyCollision(hero, prize);
    }

    @Override
    public Collision visit(Prize prize) {
        return new RegularCollision(prize.ball,this.prize.ball);
    }
}
