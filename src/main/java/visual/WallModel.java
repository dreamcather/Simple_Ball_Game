package visual;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class WallModel extends Model {
    Line line;
    Point start;
    Point end;

    public WallModel(Point start, Point end, AnchorPane anchorPane) {
        line = new Line(start.getX(),start.getY(),end.getX(),end.getY());
        anchorPane.getChildren().add(line);
        this.anchorPane =anchorPane;
        this.start =start;
        this.end = end;
    }


    @Override
    public void refresh(VisualInformation visualInformation, Camera camera) {
        try {

            line.setStartX(visualInformation.wallStart.getX());
            line.setStartY(visualInformation.wallStart.getY());
            line.setEndX(visualInformation.wallEnd.getX());
            line.setEndY(visualInformation.wallEnd.getY());
        }
        catch (Exception e){}
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(line);
    }

}
