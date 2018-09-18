package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;


public class SquareModel extends Model {
    Rectangle model;

    public SquareModel(double xCoordinate, double yCoordinate, double radius, Paint color, AnchorPane anchorPane) {
        model = new Rectangle(xCoordinate-radius,yCoordinate-radius,2*radius,2*radius);
        model.setStroke(color);
        anchorPane.getChildren().add(model);
        this.anchorPane =anchorPane;
    }

    @Override
    public void move(double xOffset, double yOffset) {
        model.setX(xOffset);
        model.setY(yOffset);
    }

    @Override
    public void hide() {
        anchorPane.getChildren().remove(model);
    }
}
