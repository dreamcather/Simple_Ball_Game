package interaction;

import gameObject.Enemy;
import gameObject.Player;

public class KillCollision implements Collision {
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
