package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class EnemyModel extends CircleModel {
    static private Color enemyColor = Color.RED;

    public EnemyModel(MyPoint center, double radius, AnchorPane anchorPane, String type) {
        super(center, radius,enemyColor, anchorPane, type);
    }
}
