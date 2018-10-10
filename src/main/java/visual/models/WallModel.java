package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import visual.models.Model;
import visual.visualInformation.VisualInformation;
import visual.visualInformation.WallVisualInformation;

public class WallModel extends Model {
    Line line;
    MyPoint start;
    MyPoint end;

    public WallModel(MyPoint start, MyPoint end, AnchorPane anchorPane) {
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
