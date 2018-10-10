package interaction;

import gameObject.Ball;
import gameObject.Player;

public class DestroyCollision implements Collision {
    private Player hero;
    private Ball pray;

    public DestroyCollision(Player main, Ball pray) {
        this.hero = main;
        this.pray = pray;
    }

    @Override
    public void collide() {
        hero.incrementPrizeCount();
        pray.setLiveStatus(false);

    }
}
