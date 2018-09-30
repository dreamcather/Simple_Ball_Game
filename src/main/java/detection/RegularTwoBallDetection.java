package detection;

import gameObject.Ball;
import geometry.GeometricalCalculation;

public class RegularTwoBallDetection implements Detection {
    Ball first;
    Ball second;

    public RegularTwoBallDetection(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean detect() {
        double distanceBetweenBall = GeometricalCalculation.getDistanceBetweenTwoPoint(first.getPosition(),second.getPosition());
        double radiusSum = first.getRadius() + second.getRadius();
        if (distanceBetweenBall < radiusSum)
            return true;
        else
            return false;
    }
}
