package interaction;

import object.Enemy;
import object.Hero;
import object.Prize;

public interface BallVisitor<T> {
    T visit(Enemy enemy);

    T visit(Hero hero);

    T visit(Prize prize);
}
