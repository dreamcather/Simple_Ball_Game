package sample;

public abstract class Model {

    private double xCenter;

    private double yCenter;

    public abstract void Move(double xOffset, double yOffset);

    public abstract void ShowElement(GameState gameState);

    public double getxCenter(){
        return xCenter;
    };

    public double getyCenter(){
        return yCenter;
    };


}
