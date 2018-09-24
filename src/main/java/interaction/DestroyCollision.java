package interaction;

import object.Ball;
import object.Player;
import object.Prize;

public class DestroyCollision extends Collision {
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
