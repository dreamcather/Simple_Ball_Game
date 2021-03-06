package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import visual.visualInformation.VisualInformation;
import visual.visualInformation.WallVisualInformation;

public class WallModel extends Model {
    private final Line line;

    public WallModel(MyPoint start, MyPoint end, AnchorPane anchorPane) {
        super();
        line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        anchorPane.getChildren().add(line);
        this.anchorPane = anchorPane;
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        WallVisualInformation wallVisualInformation = (WallVisualInformation) visualInformation;
        line.setStartX(wallVisualInformation.getStart().getX());
        line.setStartY(wallVisualInformation.getStart().getY());
        line.setEndX(wallVisualInformation.getEnd().getX());
        line.setEndY(wallVisualInformation.getEnd().getY());
        line.setVisible(true);
    }

    @Override
    public void hide() {
        line.setVisible(false);
    }

}
