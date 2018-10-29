package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.PlayerVisualInformation;
import visual.visualInformation.VisualInformation;

public class PlayerModel extends CircleModel {
    static private final Color playerColor = Color.BLUE;
    static private final Color closedPlayerColor = Color.GRAY;
    private boolean closed;

    public PlayerModel(MyPoint center, double radius, AnchorPane anchorPane) {
        super(center, radius, closedPlayerColor, anchorPane);
        closed = true;
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        PlayerVisualInformation ballVisualInformation = (PlayerVisualInformation) visualInformation;
        model.setCenterX(ballVisualInformation.getPosition().getX());
        model.setCenterY(ballVisualInformation.getPosition().getY());
        model.setRadius(((BallVisualInformation) visualInformation).getRadius());
        if(closed!=ballVisualInformation.isClosed()) {
            closed =ballVisualInformation.isClosed();
            if (ballVisualInformation.isClosed()) model.setFill(closedPlayerColor);
            else model.setFill(playerColor);
        }
    }
}
