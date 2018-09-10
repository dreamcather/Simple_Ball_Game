package sample;

import javafx.scene.paint.Color;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void collide() {
        first.xCoefficient*=-1;
        second.yCoefficient*=-1;


    }
}
