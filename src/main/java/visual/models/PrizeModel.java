package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PrizeModel extends CircleModel {
    static private Color prizeColor = Color.GREEN;

    public PrizeModel(MyPoint center, double radius, AnchorPane anchorPane) {
        super(center, radius, prizeColor, anchorPane);
    }
}
