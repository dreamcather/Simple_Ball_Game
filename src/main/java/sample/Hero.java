package sample;

public class Hero extends VisualBall {

    int score;
    CircleModel model;
    Ball ball;

    public Hero(CircleModel model,double xCoefficient, double yCoefficient, double speed,KeyboardSubscription keyboardSubscription) {
    }
    public int getScore() {
        return score;
    }


//    private void move(KeyCode keyCode) {
//        if (keyCode == KeyCode.LEFT) {
//            xCoefficient = xCoefficient * Math.cos(Math.toRadians(-15)) - yCoefficient * Math.sin(Math.toRadians(-15));
//            yCoefficient = xCoefficient * Math.sin(Math.toRadians(-15)) + yCoefficient * Math.cos(Math.toRadians(-15));
//            norm();
//        }
//        if (keyCode == KeyCode.RIGHT) {
//            xCoefficient = xCoefficient * Math.cos(Math.toRadians(15)) - yCoefficient * Math.sin(Math.toRadians(15));
//            yCoefficient = xCoefficient * Math.sin(Math.toRadians(15)) + yCoefficient * Math.cos(Math.toRadians(15));
//            norm();
//        }
//        if (keyCode == KeyCode.UP) {
//            speedOfMotion += 0.1;
//        }
//        if (keyCode == KeyCode.DOWN) {
//            speedOfMotion -= 0.1;
//        }
//
//    }

    public void addPoint() {
        score++;
    }

    @Override
    public void update() {
        model.move(ball.getPosition().getX(),ball.getPosition().getY());
    }

    @Override
    public <T> T accept(BallVisitor<T> ballVisitor) {
        return ballVisitor.visit(this);
    }
}
