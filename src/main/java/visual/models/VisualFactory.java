package visual.models;

import javafx.scene.layout.AnchorPane;
import visual.visualInformation.*;

public class VisualFactory {
    private final AnchorPane anchorPane;

    public VisualFactory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Model create(VisualInformation visualInformation) {
        if (visualInformation.getGameObject().type.equals("E")) {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new EnemyModel(ballVisualInformation.getPosition(), ballVisualInformation.getRadius(), anchorPane);
        }
        if (visualInformation.getGameObject().type.equals("P")) {
            BallVisualInformation ballVisualInformation = (PlayerVisualInformation) visualInformation;
            return new PlayerModel(ballVisualInformation.getPosition(), ballVisualInformation.getRadius(), anchorPane);
        }
        if (visualInformation.getGameObject().type.equals("Pr")) {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new PrizeModel(ballVisualInformation.getPosition(), ballVisualInformation.getRadius(), anchorPane);
        }
        if (visualInformation.getGameObject().type.equals("W")) {
            WallVisualInformation wallVisualInformation = (WallVisualInformation) visualInformation;
            return new WallModel(wallVisualInformation.getStart(), wallVisualInformation.getEnd(), anchorPane);
        }
        if (visualInformation.getGameObject().type.equals("CW")) {
            ClosedWallVisualInformation closedWallVisualInformation = (ClosedWallVisualInformation) visualInformation;
            return new ClosedWallModel(closedWallVisualInformation.getPoints(), anchorPane);
        }
        return null;
    }

}
