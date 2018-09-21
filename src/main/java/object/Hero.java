package object;

import geometry.Point;
import geometry.Vector;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import interaction.BallVisitor;

public class Hero extends Ball {

    private int score;

    public Hero(double _x,
                double _y,
                double _speed,
                double xCoordinate,
                double yCoordinate,
                double radius,
                KeyboardSubscription keyboardSubscription) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius);
        keyboardSubscription.subscribeToKey(this::move);
        score = 0;
    }

    public int getScore() {
        return score;
    }

    private void move(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            xCoefficient = xCoefficient * Math.cos(Math.toRadians(-15)) - yCoefficient * Math.sin(Math.toRadians(-15));
            yCoefficient = xCoefficient * Math.sin(Math.toRadians(-15)) + yCoefficient * Math.cos(Math.toRadians(-15));
            norm();
        }
        if (keyCode == KeyCode.RIGHT) {
            xCoefficient = xCoefficient * Math.cos(Math.toRadians(15)) - yCoefficient * Math.sin(Math.toRadians(15));
            yCoefficient = xCoefficient * Math.sin(Math.toRadians(15)) + yCoefficient * Math.cos(Math.toRadians(15));
            norm();
        }
        if (keyCode == KeyCode.UP) {
            speedOfMotion += 0.1;
        }
        if (keyCode == KeyCode.DOWN) {
            speedOfMotion -= 0.1;
        }

    }

    public void setMouseEvent(MouseEvent event) {
        Vector motion = new Vector(this.getPosition(), new Point(event.getSceneX(), event.getSceneY()));
        motion.norm();
        this.changeVector(motion);
    }

    public void incrementPrizeCount() {
        score++;
    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
