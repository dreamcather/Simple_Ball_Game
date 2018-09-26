package object;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class WallModel extends Model {

    public WallModel(Point start, Point end, AnchorPane anchorPane) {
        Line line = new Line(start.getX(),start.getY(),end.getX(),end.getY());
        anchorPane.getChildren().add(line);
    }

    @Override
    public void refresh(double xOffset, double yOffset) {

    }

    @Override
    public void hide() {

    }
}
