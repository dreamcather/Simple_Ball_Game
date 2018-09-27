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
        this.start =start;
        this.end = end;
    }


    @Override
    public void refresh(VisualInformation visualInformation, Camera camera) {
        line.setStartX(visualInformation.wallStart.getX()-camera.getPosition().getX()+250);
        line.setStartY(visualInformation.wallStart.getY()-camera.getPosition().getY()+250);
        line.setEndX(visualInformation.wallEnd.getX()-camera.getPosition().getX()+250);
        line.setEndY(visualInformation.wallEnd.getY()-camera.getPosition().getY()+250);
    }

    @Override
    public void hide() {

    }

}
