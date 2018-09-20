package sample;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    private void normalize() {
        if (first.getPosition().getDistancePoint(second.getPosition()) < first.getRadius() + second.getRadius()) {
            double speedSum = first.getSpeedOfMotion() + second.getSpeedOfMotion();
            double firstProportion = first.getSpeedOfMotion() / speedSum;
            double secondProportion = second.getSpeedOfMotion() / speedSum;
            Vector firstToSecond = new Vector(first.getPosition(), second.getPosition());
            Vector secondToFirst = new Vector(second.getPosition(), first.getPosition());
            double length = first.getRadius() + second.getRadius()
                    - first.getPosition().getDistancePoint(second.getPosition());
            firstToSecond.setLength(length * firstProportion);
            secondToFirst.setLength(length * secondProportion);
            first.setPosition(secondToFirst.addition(first.getPosition()));
            second.setPosition(firstToSecond.addition(second.getPosition()));
        }
    }

    @Override
    public void collide() {
        normalize();

        Line perpendicular = new Line(first.getPosition(), second.getPosition());
        Line firstParallel = new Line(first.getPosition(), perpendicular.getNormal());
        Line secondParallel = new Line(second.getPosition(), perpendicular.getNormal());

        Point firstEnd = first.getFuturePosition();
        Point secondEnd = second.getFuturePosition();

        Point firstPerpendicularPoint = firstParallel.getProjectionPoint(firstEnd);
        Point firstParallelPoint = perpendicular.getProjectionPoint(firstEnd);

        Point secondPerpendicularPoint = secondParallel.getProjectionPoint(secondEnd);
        Point secondParallelPoint = perpendicular.getProjectionPoint(secondEnd);

        Vector firstParallelVector = new Vector(first.getPosition(), firstParallelPoint);
        Vector firstPerpendicularVector = new Vector(first.getPosition(), firstPerpendicularPoint);

        Vector secondParallelVector = new Vector(second.getPosition(), secondParallelPoint);
        Vector secondPerpendicularVector = new Vector(second.getPosition(), secondPerpendicularPoint);

        double lengthFirst = firstPerpendicularVector.addition(secondParallelVector).getLength();
        double lengthSecond = secondPerpendicularVector.addition(firstParallelVector).getLength();

        first.changeVector(firstPerpendicularVector.addition(secondParallelVector));
        second.changeVector(secondPerpendicularVector.addition(firstParallelVector));
        first.setSpeedOfMotion(lengthFirst);
        second.setSpeedOfMotion(lengthSecond);

    }
}
