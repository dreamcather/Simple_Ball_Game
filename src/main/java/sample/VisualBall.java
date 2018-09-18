package sample;

public class VisualBall {
    Ball ball;
    Model model;

    public VisualBall(Ball ball,Model model) {
        this.ball =ball;
        this.model =model;
    }

    public void update(){
        if(!ball.isAlive()){
            model.hide();
        }
        else
        model.move(ball.getPosition().getX(),ball.getPosition().getY());
    };

}
