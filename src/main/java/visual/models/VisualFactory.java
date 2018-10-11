package visual.models;

import javafx.scene.layout.AnchorPane;
import visual.visualInformation.*;

public class VisualFactory {
    private AnchorPane anchorPane;

    public VisualFactory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Model create(VisualInformation visualInformation) {
        if (visualInformation.type.equals("E")) {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new EnemyModel(ballVisualInformation.getPosition(), 15,anchorPane, "E");
        }
        if (visualInformation.type.equals("P")) {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new PlayerModel(ballVisualInformation.getPosition(), 15, anchorPane, "P");
        }
        if (visualInformation.type.equals("Pr")) {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new PrizeModel(ballVisualInformation.getPosition(), 15, anchorPane, "Pr");
        }
        if (visualInformation.type.equals("W")) {
            WallVisualInformation wallVisualInformation = (WallVisualInformation) visualInformation;
            return new WallModel(wallVisualInformation.getStart(), wallVisualInformation.getEnd(), anchorPane);
        }
        if(visualInformation.type.equals("CW"))
        {
            ClosedWallVisualInformation closedWallVisualInformation = (ClosedWallVisualInformation)visualInformation;
            return new ClosedWallModel(closedWallVisualInformation.getPoints(),anchorPane);
        }
        return null;
    }

}