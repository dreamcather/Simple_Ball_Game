package sample;

public class HeroVisiter implements BallVisitor<Collision> {
    Hero hero;

    public HeroVisiter(Hero hero) {
        this.hero = hero;
    }

    @Override
    public Collision visit(Enemy enemy) {
        return new RegularCollision(hero.ball,enemy.ball);
    }

    @Override
    public Collision visit(Hero hero) {
        return null;
    }

    @Override
    public Collision visit(Prize prize) {
        return new DestroyCollision(hero,prize);
    }
}
