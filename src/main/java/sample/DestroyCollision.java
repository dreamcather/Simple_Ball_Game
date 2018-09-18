package sample;

public class DestroyCollision extends Collision {
    Hero hero;
    Prize pray;

    public DestroyCollision(Hero main, Prize pray) {
        this.hero = main;
        this.pray = pray;
    }

    @Override
    public void collide() {
        hero.addPoint();
        pray.alive =false;

    }
}
