package interaction;

import gameObject.Player;
import gameObject.Prize;

public class DestroyCollision implements Interaction {
    private Player player;
    private Prize prize;

    public DestroyCollision(Player player, Prize prize) {
        this.player = player;
        this.prize = prize;
    }

    @Override
    public boolean detect() {
        return new RegularTwoBallDetection(player, prize).detect();
    }

    @Override
    public void collision() {
        player.incrementPrizeCount();
        player.addLife();
        prize.setLiveStatus(false);

    }
}
