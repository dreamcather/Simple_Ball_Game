package interaction;

import gameObject.Ball;
import geometry.GeometricalCalculation;

public class RegularTwoBallDetection {
    private Ball first;
    private Ball second;

    public RegularTwoBallDetection(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }
    public boolean detect(){
        double distanceBetweenBall = GeometricalCalculation.getDistanceBetweenTwoPoint(first.getPosition(),second.getPosition());
        double radiusSum = first.getRadius() + second.getRadius();
        return distanceBetweenBall < radiusSum;
    }
}
