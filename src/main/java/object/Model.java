package object;


import javafx.scene.layout.AnchorPane;

public abstract class Model {
    protected AnchorPane anchorPane;

    public abstract void refresh(double xOffset, double yOffset);

    public abstract void hide();

}
