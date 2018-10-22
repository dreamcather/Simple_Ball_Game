package gameObject;

import geometry.Vector;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;

public class Player extends Ball {

    private int score;
    private int lifeCount;

    public Player(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, double radius, int key) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius, key);
        score = 0;
        lifeCount = 3;
        type = "P";
    }

    public int getScore() {
        return score;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void eraseLife() {
        this.lifeCount--;
    }

    public void incrementPrizeCount() {
        score++;
    }

    @Override
    public <T> T collision(ObjectInteractionVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public void move() {
        xCoordinate += xCoefficient * speedOfMotion;
        yCoordinate += yCoefficient * speedOfMotion;
    }

    public void setMotionControl(MotionControl motionControl) {
        if (motionControl.getPosition() != null) {
            Vector motion = new Vector(this.getPosition(), motionControl.getPosition());
            motion.norm();
            this.changeVector(motion);
            motionControl.setPosition(null);
        }
    }

    public void kill() {
        alive = false;
    }
}
