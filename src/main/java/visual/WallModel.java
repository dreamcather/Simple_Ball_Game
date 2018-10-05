package visual;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class WallModel extends Model {
    Line line;
    Point start;
    Point end;

    public WallModel(Point start, Point end, AnchorPane anchorPane) {
        super();
        line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        anchorPane.getChildren().add(line);
        this.anchorPane = anchorPane;
        this.start = start;
        this.end = end;
        type = "W";
    }


    @Override
    public void refresh(VisualInformation visualInformation) {
        try {
            WallVisualInformation wallVisualInformation = (WallVisualInformation) visualInformation;
            line.setStartX(wallVisualInformation.getStart().getX());
            line.setStartY(wallVisualInformation.getStart().getY());
            line.setEndX(wallVisualInformation.getEnd().getX());
            line.setEndY(wallVisualInformation.getEnd().getY());
        } catch (Exception e) {
        }
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(line);
    }

}
