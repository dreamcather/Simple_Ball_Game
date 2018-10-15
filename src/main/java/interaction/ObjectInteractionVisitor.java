package interaction;

import gameObject.*;
import visual.Camera;
import visual.visualInformation.VisualInformation;

public interface ObjectInteractionVisitor<T> {

    T visit(Enemy enemy);

    T visit(Player player);

    T visit(Prize prize);

    T visit(Wall wall);

    T visit(ClosedWall closedWall);
}
