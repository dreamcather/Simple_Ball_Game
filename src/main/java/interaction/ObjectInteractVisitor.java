package interaction;

import gameObject.*;

public interface ObjectInteractVisitor<T> {

    T visit(Enemy enemy);

    T visit(Player player);

    T visit(Prize prize);

    T visit(Wall wall);
}
