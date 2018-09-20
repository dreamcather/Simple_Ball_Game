package sample;


import javafx.scene.layout.AnchorPane;

public abstract class Model {
    protected AnchorPane anchorPane;

    public abstract void move(double xOffset, double yOffset);

    public abstract void hide();

}
