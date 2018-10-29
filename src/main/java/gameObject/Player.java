package gameObject;

import geometry.Vector;
import control.MotionControl;
import interaction.ObjectInteractionVisitor;
import javafx.scene.input.KeyCode;
import visual.Camera;
import visual.visualInformation.BallVisualInformation;
import visual.visualInformation.PlayerVisualInformation;
import visual.visualInformation.VisualInformation;

public class Player extends Ball {

    private int score;
    private int lifeCount;
    private boolean clossed;

    public Player(double _x, double _y, double _speed, double xCoordinate, double yCoordinate, int radius, int key) {
        super(_x, _y, _speed, xCoordinate, yCoordinate, radius, key);
        score = 0;
        lifeCount = 1;
        clossed=false;
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

    public void addLife(){
        if(lifeCount<10)
            lifeCount++;
    }

    public void incrementPrizeCount() {
        score++;
    }

    public void growUp(){
        radius+=5;
    }

    private void changeclossedFlag(){
        clossed=!clossed;
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
        if(motionControl.getKey()== KeyCode.SPACE){
            changeclossedFlag();
            motionControl.setKey(null);
        }
    }

    public void kill() {
        alive = false;
    }

    public boolean isClossed() {
        return clossed;
    }

    @Override
    public VisualInformation isVisible(Camera camera) {
        BallVisualInformation visualInformation= (BallVisualInformation) super.isVisible(camera);
        return new PlayerVisualInformation(visualInformation.getPosition(),visualInformation.getRadius(),clossed,this);
    }
}
