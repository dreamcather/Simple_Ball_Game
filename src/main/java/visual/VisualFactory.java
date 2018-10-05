package visual;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class VisualFactory {
    private AnchorPane anchorPane;
    private Color enemyColor = Color.RED;
    private Color playerColor = Color.BLUE;
    private Color prizeColor = Color.GREEN;

    public VisualFactory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Model create(VisualInformation visualInformation) {
        if (visualInformation.type == "E") {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new CircleModel(ballVisualInformation.getPosition(), 15, enemyColor, anchorPane, "E");
        }
        if (visualInformation.type == "P") {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new CircleModel(ballVisualInformation.getPosition(), 15, playerColor, anchorPane, "P");
        }
        if (visualInformation.type == "Pr") {
            BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
            return new CircleModel(ballVisualInformation.getPosition(), 15, prizeColor, anchorPane, "Pr");
        }
        if (visualInformation.type == "W") {
            WallVisualInformation wallVisualInformation = (WallVisualInformation) visualInformation;
            return new WallModel(wallVisualInformation.start, wallVisualInformation.end, anchorPane);
        }
        if(visualInformation.type =="CW")
        {
            ClosedWallVisualInformation closedWallVisualInformation = (ClosedWallVisualInformation)visualInformation;
            return new ClosedWallModel(closedWallVisualInformation.points,anchorPane);
        }
        return null;
    }

}
