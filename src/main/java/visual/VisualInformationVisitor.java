package visual;

import visual.models.*;
import visual.visualInformation.*;

public interface VisualInformationVisitor {
    EnemyModel visit(EnemyVisualInformation enemyVisualInformation);

    PlayerModel visit(PlayerVisualInformation playerVisualInformation);

    PrizeModel visit(PrizeVisualInformation prizeVisualInformation);

    WallModel visit(WallVisualInformation wallVisualInformation);

    ClosedWallModel visit(ClosedWallVisualInformation closedWallVisualInformation);
}
