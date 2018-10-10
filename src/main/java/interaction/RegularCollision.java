package interaction;

import geometry.GeometricalCalculation;
import geometry.Line;
import geometry.MyPoint;
import geometry.Vector;
import gameObject.Ball;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    private double getDistanceBetweenBall() {
        return GeometricalCalculation.getDistanceBetweenTwoPoint(first.getPosition(), second.getPosition());
    }

    private void correctionPosition() {
        if (this.getDistanceBetweenBall() < first.getRadius() + second.getRadius()) {
            double speedSum = first.getSpeedOfMotion() + second.getSpeedOfMotion();
            double firstProportion = first.getSpeedOfMotion() / speedSum;
            double secondProportion = second.getSpeedOfMotion() / speedSum;
            Vector firstToSecond = new Vector(first.getPosition(), second.getPosition());
            Vector secondToFirst = new Vector(second.getPosition(), first.getPosition());
            double length = first.getRadius() + second.getRadius() - this.getDistanceBetweenBall();
            firstToSecond.setLength(length * firstProportion);
            secondToFirst.setLength(length * secondProportion);
            first.setPosition(secondToFirst.getEndPointVector(first.getPosition()));
            second.setPosition(firstToSecond.getEndPointVector(second.getPosition()));
        }
    }

    @Override
    public void collide() {
        correctionPosition();

        Line lineParallelCollideThroughBallsCenter = new Line(first.getPosition(), second.getPosition());
        Line linePerpendicularCollideThroughFirstBallCenter = new Line(first.getPosition(),
                lineParallelCollideThroughBallsCenter.getNormal());
        Line linePerpendicularCollideThroughSecondBallCenter = new Line(second.getPosition(),
                lineParallelCollideThroughBallsCenter.getNormal());

        MyPoint pointFirstBallVectorEnd = first.getFuturePosition();
        MyPoint pointSecondBallVectorEnd = second.getFuturePosition();

        MyPoint pointFirstBallVectorEndProjectionOnLinePerpendicularCollide = linePerpendicularCollideThroughFirstBallCenter.getProjectionPointToLine(pointFirstBallVectorEnd);
        MyPoint pointFirstBallVectorEndProjectionOnLineParallelCollide = lineParallelCollideThroughBallsCenter.getProjectionPointToLine(pointFirstBallVectorEnd);

        MyPoint pointSecondBallVectorEndProjectionOnLinePerpendicularCollide = linePerpendicularCollideThroughSecondBallCenter.getProjectionPointToLine(pointSecondBallVectorEnd);
        MyPoint pointSecondBallVectorEndProjectionOnLineParallelCollide = lineParallelCollideThroughBallsCenter.getProjectionPointToLine(pointSecondBallVectorEnd);

        Vector firstBallVectorProjectionOnLineParallelCollide = new Vector(first.getPosition(),
                pointFirstBallVectorEndProjectionOnLineParallelCollide);
        Vector firstBallVectorProjectionOnLinePerpendicularCollide = new Vector(first.getPosition(),
                pointFirstBallVectorEndProjectionOnLinePerpendicularCollide);

        Vector secondBallVectorProjectionOnLineParallelCollide = new Vector(second.getPosition(),
                pointSecondBallVectorEndProjectionOnLineParallelCollide);
        Vector secondBallVectorProjectionOnLinePerpendicularCollide = new Vector(second.getPosition(),
                pointSecondBallVectorEndProjectionOnLinePerpendicularCollide);

        Vector firstBallResultVector = GeometricalCalculation.vectorSum(firstBallVectorProjectionOnLinePerpendicularCollide,secondBallVectorProjectionOnLineParallelCollide);
        Vector secondBallResultVector = GeometricalCalculation.vectorSum(secondBallVectorProjectionOnLinePerpendicularCollide,firstBallVectorProjectionOnLineParallelCollide);

        double firstBallResultVectorLength = firstBallResultVector.getLength();
        double secondBallResultVectorLength = secondBallResultVector.getLength();

        first.changeVector(firstBallResultVector);
        second.changeVector(secondBallResultVector);
        first.setSpeedOfMotion(firstBallResultVectorLength);
        second.setSpeedOfMotion(secondBallResultVectorLength);

    }
}
