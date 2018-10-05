package visual;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleModel extends Model {
    private Circle model;

    public CircleModel(Point center, double radius, Color color, AnchorPane anchorPane, String type) {
        super();
        model = new Circle(center.getX(), center.getY(), radius, color);
        this.anchorPane = anchorPane;
        anchorPane.getChildren().add(model);
        this.type = type;
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
        model.setCenterX(ballVisualInformation.getPosition().getX());
        model.setCenterY(ballVisualInformation.getPosition().getY());
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(model);
    }

}
