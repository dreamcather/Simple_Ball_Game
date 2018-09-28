package object;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleModel extends Model {
    private Circle model;

    CircleModel(Point center, double radius, Color color, AnchorPane anchorPane) {
        model = new Circle(center.getX(), center.getY(), radius, color);
        this.anchorPane = anchorPane;
        anchorPane.getChildren().add(model);
    }

    @Override
    public void refresh(double xOffset, double yOffset) {
        model.setCenterX(xOffset);
        model.setCenterY(yOffset);
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(model);
    }

}
