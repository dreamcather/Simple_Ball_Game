package interaction;

import gameObject.Ball;
import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.MyPoint;
import geometry.Vector;

public class RegularTwoBallInteraction implements Interaction {
    private Ball first;
    private Ball second;

    public RegularTwoBallInteraction(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean detect() {
        double distanceBetweenBall = GeometricalCalculation.getDistanceBetweenTwoPoint(first.getPosition(),second.getPosition());
        double radiusSum = first.getRadius() + second.getRadius();
        return distanceBetweenBall < radiusSum;
    }

    @Override
    public void collision() {
        new RegularTwoBallCollision(first,second).collide();
    }
}
