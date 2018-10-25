package visual.models;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.VisualInformation;

public class CircleModel extends Model {
    private Circle model;

    public CircleModel(MyPoint center, double radius, Color color, AnchorPane anchorPane) {
        super();
        model = new Circle(center.getX(), center.getY(), radius, color);
        this.anchorPane = anchorPane;
        anchorPane.getChildren().add(model);
    }

    @Override
    public void refresh(VisualInformation visualInformation) {
        BallVisualInformation ballVisualInformation = (BallVisualInformation) visualInformation;
        model.setCenterX(ballVisualInformation.getPosition().getX());
        model.setCenterY(ballVisualInformation.getPosition().getY());
        model.setRadius(((BallVisualInformation) visualInformation).getRadius());
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(model);
    }

}
