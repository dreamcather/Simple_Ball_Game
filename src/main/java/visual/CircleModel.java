package visual;

import geometry.Point;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleModel extends Model {
    private Circle model;
    public int mark;

    public CircleModel(Point center, double radius, Color color, AnchorPane anchorPane) {
        model = new Circle(center.getX(), center.getY(), radius, color);
        this.anchorPane = anchorPane;
        anchorPane.getChildren().add(model);
    }


    @Override
    public void refresh(VisualInformation visualInformation, Camera camera) {
        model.setCenterX(visualInformation.ballPoint.getX()-camera.getPosition().getX()+250);
        model.setCenterY(visualInformation.ballPoint.getY()-camera.getPosition().getY()+250);
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(model);
    }

}
