package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.PlayerVisualInformation;
import visual.visualInformation.VisualInformation;

public class PlayerModel extends CircleModel {
    static private Color playerColor = Color.BLUE;
    static private Color closedPlayerColor = Color.GRAY;
    private boolean closed;

    public PlayerModel(MyPoint center, double radius, AnchorPane anchorPane) {
        super(center, radius, playerColor, anchorPane);
        closed = false;
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        PlayerVisualInformation ballVisualInformation = (PlayerVisualInformation) visualInformation;
        model.setCenterX(ballVisualInformation.getPosition().getX());
        model.setCenterY(ballVisualInformation.getPosition().getY());
        model.setRadius(((BallVisualInformation) visualInformation).getRadius());
        if(closed!=ballVisualInformation.isClossed()) {
            closed =ballVisualInformation.isClossed();
            if (ballVisualInformation.isClossed()) model.setFill(closedPlayerColor);
            else model.setFill(playerColor);
        }
    }
}
