package sample;

public abstract class VisualBall {
    Ball ball;
    Model model;
    boolean alive;

    public VisualBall(Model model,double xCoefficientm, double yCoefficient, double speed) {
        this.model = model;
        ball = new Ball(xCoefficientm,yCoefficient,speed,model.getXCoordinate(),model.getYCoordinate(),model.getRadius());
        alive = true;
    }

    public void update(){
        model.move(ball.getPosition().getX(),ball.getPosition().getY());
    };

    public abstract <T> T accept(BallVisitor<T> ballVisitor);

    public void move(){
        ball.move();
        update();
    }

    public boolean isAlive(){
        return alive;
    }
}
