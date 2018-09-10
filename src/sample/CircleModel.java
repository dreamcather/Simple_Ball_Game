package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleModel extends Model {
    Circle model;
    CircleModel(double xCoordinateCenter, double yCoordinateCenter, double radius, Color color,AnchorPane anchorPane){
        model = new Circle(xCoordinateCenter,yCoordinateCenter,radius,color);
        anchorPane.getChildren().add(model);
    }
    @Override
    public void move(double xOffset, double yOffset) {
        model.setCenterX(model.getCenterX() + xOffset);
        model.setCenterY(model.getCenterY() + yOffset);
    }
}
