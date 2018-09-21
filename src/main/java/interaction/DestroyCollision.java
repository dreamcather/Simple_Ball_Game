package interaction;

import object.Hero;
import object.Prize;

public class DestroyCollision extends Collision {
    private Hero hero;
    private Prize pray;

    public DestroyCollision(Hero main, Prize pray) {
        this.hero = main;
        this.pray = pray;
    }

    @Override
    public void collide() {
        hero.incrementPrizeCount();
        pray.setLiveStatus(false);

    }
}
