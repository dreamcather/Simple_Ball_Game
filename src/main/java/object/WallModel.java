package object;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import visual.Camera;
import visual.Model;
import visual.VisualInformation;

public class WallModel extends Model {

    public WallModel(Point start, Point end, AnchorPane anchorPane) {
        Line line = new Line(start.getX(),start.getY(),end.getX(),end.getY());
        anchorPane.getChildren().add(line);
    }

    @Override
    public void refresh(VisualInformation visualInformation, Camera camera) {

    }

    @Override
    public void hide() {

    }
}
