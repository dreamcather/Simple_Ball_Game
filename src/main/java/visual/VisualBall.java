package visual;

import gameObject.Ball;

public class VisualBall {
    Ball ball;
    Model model;

    public VisualBall(Ball ball, Model model) {
        this.ball = ball;
        this.model = model;
    }

    public void update() {
        if (!ball.isAlive()) {
            model.hide();
        } else
            model.refresh(ball.getPosition().getX(), ball.getPosition().getY());
    };

}