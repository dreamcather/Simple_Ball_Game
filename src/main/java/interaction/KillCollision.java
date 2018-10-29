package interaction;

import gameObject.Enemy;
import gameObject.Player;

public class KillCollision implements Interaction {
    private final Player player;
    private final Enemy enemy;

    public KillCollision(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public boolean detect() {
        return new RegularTwoBallDetection(player, enemy).detect();
    }

    @Override
    public void collision() {
        if(player.isClosed()){
            new RegularTwoBallCollision(player,enemy).collide();
        }
        else {
            player.eraseLife();
            new RegularTwoBallCollision(player, enemy).collide();
        }

    }
}
