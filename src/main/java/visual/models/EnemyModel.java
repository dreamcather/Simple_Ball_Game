package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class EnemyModel extends CircleModel {
    static private final Color enemyColor = Color.RED;

    public EnemyModel(MyPoint center, double radius, AnchorPane anchorPane) {
        super(center, radius, enemyColor, anchorPane);
    }
}
