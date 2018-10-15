package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PlayerModel extends CircleModel {
    static private Color playerColor = Color.BLUE;

    public PlayerModel(MyPoint center, double radius, AnchorPane anchorPane) {
        super(center, radius, playerColor, anchorPane);
    }
}
