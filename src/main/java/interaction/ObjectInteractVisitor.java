package interaction;

import gameObject.*;
import visual.Camera;
import visual.VisualInformation;

public interface ObjectInteractVisitor<T> {

    T visit(Enemy enemy);

    T visit(Player player);

    T visit(Prize prize);

    T visit(Wall wall);

    T visit(ClosedWall closedWall);

    VisualInformation isVisible(Camera camera);
}
