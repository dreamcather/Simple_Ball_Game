package sample;

import javafx.scene.layout.AnchorPane;

public abstract class Model {

    private double xCenter;

    private double yCenter;

    public abstract void move(double xOffset, double yOffset);

    public abstract void showElement(AnchorPane anchorPane);

    public double getxCenter(){
        return xCenter;
    };

    public double getyCenter(){
        return yCenter;
    };


}
