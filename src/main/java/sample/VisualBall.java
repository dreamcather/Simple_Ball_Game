package sample;

import javafx.scene.shape.Circle;

public abstract class VisualBall {
    Ball ball;
    Model model;

    public abstract void update();

    public abstract <T> T accept(BallVisitor<T> ballVisitor);
}
