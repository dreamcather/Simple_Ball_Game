package sample;

import javafx.scene.layout.AnchorPane;

public abstract class Model {


    public abstract void move(double xOffset, double yOffset);

    public abstract void hide(AnchorPane anchorPane);

    public abstract double getXCoordinate();

    public abstract double getYCoordinate();

    public abstract double getRadius();



}
