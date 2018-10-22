package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import visual.visualInformation.ClosedWallVisualInformation;
import visual.visualInformation.VisualInformation;

import java.util.ArrayList;

public class ClosedWallModel extends Model {
    private ArrayList<Line> lines;

    public ClosedWallModel(MyPoint[] points, AnchorPane anchorPane) {
        super();
        this.anchorPane = anchorPane;
        lines = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            lines.add(new Line(points[i].getX(), points[i].getY(), points[i + 1].getX(), points[i + 1].getY()));
        }
        for (Line line : lines)
            anchorPane.getChildren().add(line);
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        hide();
        lines.clear();
        ClosedWallVisualInformation closedWallVisualInformation = (ClosedWallVisualInformation) visualInformation;
        for (int i = 0; i < closedWallVisualInformation.getPoints().length - 1; i++) {
            lines.add(new Line(closedWallVisualInformation.getPoints()[i].getX(),
                               closedWallVisualInformation.getPoints()[i].getY(),
                               closedWallVisualInformation.getPoints()[i + 1].getX(),
                               closedWallVisualInformation.getPoints()[i + 1].getY()));
        }
        lines.add(new Line(closedWallVisualInformation.getPoints()[0].getX(),
                           closedWallVisualInformation.getPoints()[0].getY(),
                           closedWallVisualInformation.getPoints()[closedWallVisualInformation.getPoints().length
                                   - 1].getX(),
                           closedWallVisualInformation.getPoints()[closedWallVisualInformation.getPoints().length
                                   - 1].getY()));
        for (Line line : lines)
            anchorPane.getChildren().add(line);

    }

    @Override
    public void hide() {
        for (Line line : lines)
            anchorPane.getChildren().remove(line);
    }
}
