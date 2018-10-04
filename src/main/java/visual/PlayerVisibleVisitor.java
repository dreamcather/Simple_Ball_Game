package visual;

import gameObject.Player;

public class PlayerVisibleVisitor implements Visible {
    Player player;

    public PlayerVisibleVisitor(Player player) {
        this.player = player;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        return null;
    }
}
