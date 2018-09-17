package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Enemy extends VisualBall {

    public Enemy(Model model, double xCoefficientm, double yCoefficient, double speed) {
        super(model, xCoefficientm, yCoefficient, speed);
    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
