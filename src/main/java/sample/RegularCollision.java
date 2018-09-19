package sample;

public class RegularCollision extends Collision {
    private Ball first;
    private Ball second;

    public RegularCollision(Ball first, Ball second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void collide() {

        Line perpendicular = new Line(first.getPosition(),second.getPosition());
        Line firstParalel = new Line(first.getPosition(),perpendicular.getNormal());
        Line secondParalel = new Line(second.getPosition(),perpendicular.getNormal());

        Point firstEnd = first.getFuturePosition();
        Point secondEnd = second.getFuturePosition();

        Point firstPerpendicularPoint = firstParalel.getProectionPoint(firstEnd);
        Point firstParalelPoint = perpendicular.getProectionPoint(firstEnd);

        Point secondPerpendicularPoint = secondParalel.getProectionPoint(secondEnd);
        Point secondParalelPoint = perpendicular.getProectionPoint(secondEnd);

        Vector firstParalelVector = new Vector(first.getPosition(),firstParalelPoint);
        Vector firstPerpendicularVector = new Vector(first.getPosition(), firstPerpendicularPoint);

        Vector secondParalelVector = new Vector(second.getPosition(),secondParalelPoint);
        Vector secondPerpendicularVector = new Vector(second.getPosition(), secondPerpendicularPoint);

        double lenghtFirst =firstPerpendicularVector.addition(secondParalelVector).getLenght();
        double lenghtSecond = secondPerpendicularVector.addition(firstParalelVector).getLenght();

        first.changeVector(firstPerpendicularVector.addition(secondParalelVector));
        second.changeVector(secondPerpendicularVector.addition(firstParalelVector));
        first.setSpeedOfMotion(lenghtFirst);
        second.setSpeedOfMotion(lenghtSecond);




    }
}
