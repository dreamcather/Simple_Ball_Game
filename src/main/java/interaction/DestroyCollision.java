package interaction;

import gameObject.Player;
import gameObject.Prize;

public class DestroyCollision implements Interaction {
    private final Player player;
    private final Prize prize;

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
        if(player.isClosed()){
            new RegularTwoBallCollision(player,prize).collide();
        }
        else {
            player.incrementPrizeCount();
            player.addLife();
            player.growUp();
            prize.setLiveStatus(false);
        }

    }
}
