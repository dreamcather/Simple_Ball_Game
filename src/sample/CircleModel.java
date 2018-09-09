package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class CircleModel extends Model {
    Circle model;
    CircleModel(AnchorPane anchorPane){
        model = new Circle(100,100,5);
        anchorPane.getChildren().add(model);
    }
    @Override
    public void move(double xOffset, double yOffset) {
        model.setCenterX(model.getCenterX() + xOffset);
        model.setCenterY(model.getCenterY() + yOffset);
    }

    @Override
    public void showElement(AnchorPane anchorPane) {
        anchorPane.getChildren().add(model);

    }
}
