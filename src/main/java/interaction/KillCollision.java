package interaction;

import gameObject.Enemy;
import gameObject.Player;

public class KillCollision implements Interaction  {
    private Player player;
    private Enemy enemy;

    public KillCollision(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public boolean detect() {
        return new RegularTwoBallDetection(player,enemy).detect();
    }

    @Override
    public void collision() {
        player.eraseLife();
        new RegularTwoBallCollision(player,enemy).collide();

    }
}
