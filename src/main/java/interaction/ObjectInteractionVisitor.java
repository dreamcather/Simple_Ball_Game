package interaction;

import gameObject.*;

public interface ObjectInteractionVisitor<T> {

    T visit(Enemy enemy);

    T visit(Player player);

    T visit(Prize prize);

    T visit(Wall wall);

    T visit(ClosedWall closedWall);
}
