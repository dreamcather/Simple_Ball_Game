package visual;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class ClosedWallModel extends Model {
    ArrayList<Line> lines;

    public ClosedWallModel(Point[] points, AnchorPane anchorPane) {
        super();
        type = "CW";
        this.anchorPane = anchorPane;
        lines = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            lines.add(new Line(points[i].getX(), points[i].getY(), points[i + 1].getX(), points[i + 1].getY()));
        }
        lines.add(new Line(points[0].getX(),
                           points[0].getY(),
                           points[points.length - 1].getX(),
                           points[points.length - 1].getY()));
        for (int i = 0; i < lines.size(); i++)
            anchorPane.getChildren().add(lines.get(i));
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        hide();
        lines.clear();
        ClosedWallVisualInformation closedWallVisualInformation = (ClosedWallVisualInformation) visualInformation;
        for (int i = 0; i < closedWallVisualInformation.points.length - 1; i++) {
            lines.add(new Line(closedWallVisualInformation.points[i].getX(),
                               closedWallVisualInformation.points[i].getY(),
                               closedWallVisualInformation.points[i + 1].getX(),
                               closedWallVisualInformation.points[i + 1].getY()));
        }
        lines.add(new Line(closedWallVisualInformation.points[0].getX(),
                closedWallVisualInformation.points[0].getY(),
                closedWallVisualInformation.points[closedWallVisualInformation.points.length - 1].getX(),
                closedWallVisualInformation.points[closedWallVisualInformation.points.length - 1].getY()));
        for (int i = 0; i < lines.size(); i++)
            anchorPane.getChildren().add(lines.get(i));

    }

    @Override
    public void hide() {
        for (int i = 0; i < lines.size(); i++)
            anchorPane.getChildren().remove(lines.get(i));
    }
}
