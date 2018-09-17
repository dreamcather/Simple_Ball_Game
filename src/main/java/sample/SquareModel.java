package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class SquareModel extends Model {
    Rectangle model;

    public SquareModel(double xCoordinate, double yCoordinate, double radius, Paint color, AnchorPane anchorPane) {
        model = new Rectangle(xCoordinate-radius,yCoordinate-radius,2*radius,2*radius);
        model.setStroke(color);
        anchorPane.getChildren().add(model);
    }

    @Override
    public void move(double xOffset, double yOffset) {
        model.setX(xOffset);
        model.setY(yOffset);
    }

    @Override
    public void hide(AnchorPane anchorPane) {
        anchorPane.getChildren().remove(model);
    }

    @Override
    public double getXCoordinate() {
        return model.getX()+getRadius();
    }

    @Override
    public double getYCoordinate() {
        return model.getY()+getRadius();
    }

    @Override
    public double getRadius() {
        return model.getWidth();
    }
}
