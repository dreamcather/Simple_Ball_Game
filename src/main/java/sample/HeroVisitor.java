package sample;

public class HeroVisitor implements BallVisitor<Collision> {
    private Hero hero;

    public HeroVisitor(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(hero, enemy);
    }

    @Override
    public Collision visit(Hero hero) {
        return null;
    }

    @Override
    public Collision visit(Prize prize) {
        return new DestroyCollision(hero, prize);
    }
}
