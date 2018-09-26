package detection;

import gameObject.*;

public interface ObjectDetectVisitor<T> {

    T visit(Enemy enemy);

    T visit(Player player);

    T visit(Prize prize);

    T visit(Wall wall);
}
