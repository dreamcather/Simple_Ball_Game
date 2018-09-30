package visual;

import gameObject.Enemy;
import gameObject.Player;
import gameObject.Prize;
import gameObject.Wall;
import interaction.ObjectInteractVisitor;

public class VisualVisitor implements ObjectInteractVisitor<Visible> {
    @Override
    public Visible visit(Enemy enemy) {
        return new BallVisibleVisitor(enemy);
    }

    @Override
    public Visible visit(Player player) {
        return new BallVisibleVisitor(player);
    }

    @Override
    public Visible visit(Prize prize) {
        return new BallVisibleVisitor(prize);
    }

    @Override
    public Visible visit(Wall wall) {
        return new WallVisibleVisitor(wall);
    }
}
