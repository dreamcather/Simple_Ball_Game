package interaction;

import object.Enemy;
import object.Hero;

public class KillCollision extends Collision {
    Hero hero;
    Enemy enemy;

    public KillCollision(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    @Override
    public void collide() {
        hero.eraseLife();
        RegularCollision regularCollision = new RegularCollision(hero, enemy);
        regularCollision.collide();

    }
}
