package interaction;

import object.Enemy;
import object.Player;

public class KillCollision extends Collision {
    Player hero;
    Enemy enemy;

    public KillCollision(Player hero, Enemy enemy) {
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
