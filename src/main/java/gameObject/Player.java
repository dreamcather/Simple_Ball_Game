package gameObject;

import geometry.Vector;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import javafx.scene.input.KeyCode;

public class Player extends Ball {

    private int score;
    private int lifeCount;

    public Player(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius);
        score = 0;
        lifeCount = 3;
        type = "P";
    }

    public int getScore() {
        return score;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void eraseLife() {
        this.lifeCount--;
    }

    public void setKey(KeyCode keyCode) {
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
            speedOfMotion += 1;
            if(speedOfMotion>10)
                speedOfMotion=10;
        }
        if (keyCode == KeyCode.DOWN) {
            speedOfMotion -= 1;
            if(speedOfMotion<0)
                speedOfMotion=0;
        }

    }

    public void incrementPrizeCount() {
        score++;
    }

    @Override
    public <T> T collision(ObjectInteractionVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public void move(MotionControl motionControl) {
        if (motionControl.getPosition() != null) {
            Vector motion = new Vector(this.getPosition(), motionControl.getPosition());
            motion.norm();
            this.changeVector(motion);
            motionControl.setPosition(null);
        } else {
            xCoordinate += xCoefficient * speedOfMotion;
            yCoordinate += yCoefficient * speedOfMotion;
        }
    }
}
