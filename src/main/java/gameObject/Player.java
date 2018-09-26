package gameObject;

import detection.ObjectDetectVisitor;
import geometry.Vector;
import interaction.MotionControl;
import interaction.ObjectInteractVisitor;
import javafx.scene.input.KeyCode;


public class Player extends Ball {

    private int score;
    private int lifeCount;

    public Player(double _x,
                  double _y,
                  double _speed,
                  double xCoordinate,
                  double yCoordinate,
                  double radius,
                  KeyboardSubscription keyboardSubscription) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius);
        keyboardSubscription.subscribeToKey(this::move);
        score = 0;
        lifeCount = 3;
        type ="P";
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

    public void incrementPrizeCount() {
        score++;
    }

    @Override
    public <T> T collisionReaction(ObjectInteractVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }

    @Override
    public <T> T collisionDetection(ObjectDetectVisitor<T> objectDetectVisitor) {
        return objectDetectVisitor.visit(this);
    }

    @Override
    public void move(MotionControl motionControl){
        if(motionControl.getPosition()!=null){
            Vector motion = new Vector(this.getPosition(), motionControl.getPosition());
            motion.norm();
            this.changeVector(motion);
            motionControl.setPosition(null);
        }
        else{
            xCoordinate += xCoefficient * speedOfMotion;
            yCoordinate += yCoefficient * speedOfMotion;
        }
    }
}
