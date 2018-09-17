package sample;

import javafx.scene.input.KeyCode;

public class Hero extends VisualBall {

    int score;

    public Hero(Model model, double xCoefficientm, double yCoefficient, double speed, KeyboardSubscription keyboardSubscription) {
        super(model, xCoefficientm, yCoefficient, speed);
        keyboardSubscription.subscribeToKey(this::move);
    }


    public int getScore() {
        return score;
    }


    private void move(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            ball.xCoefficient = ball.xCoefficient * Math.cos(Math.toRadians(-15)) - ball.yCoefficient * Math.sin(Math.toRadians(-15));
            ball.yCoefficient = ball.xCoefficient * Math.sin(Math.toRadians(-15)) + ball.yCoefficient * Math.cos(Math.toRadians(-15));
            ball.norm();
        }
        if (keyCode == KeyCode.RIGHT) {
            ball.xCoefficient = ball.xCoefficient * Math.cos(Math.toRadians(15)) - ball.yCoefficient * Math.sin(Math.toRadians(15));
            ball.yCoefficient = ball.xCoefficient * Math.sin(Math.toRadians(15)) + ball.yCoefficient * Math.cos(Math.toRadians(15));
            ball.norm();
        }
        if (keyCode == KeyCode.UP) {
            ball.speedOfMotion += 0.1;
        }
        if (keyCode == KeyCode.DOWN) {
            ball.speedOfMotion -= 0.1;
        }

    }

    public void addPoint() {
        score++;
    }
    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
