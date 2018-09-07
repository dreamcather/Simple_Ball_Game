package sample;

import javafx.scene.shape.Circle;

public abstract class Ball {
    private double xCoefficient;
    private double yCoefficient;
    private double speedOfMotion;
    private Model gameModel;
    private GameState gameStatus;
    Ball(double _x, double _y, double _speed, GameState _game){
        xCoefficient=_x;
        yCoefficient=_y;

        speedOfMotion=_speed;
        gameStatus = _game;
    }
    private void Move(){
        gameModel.Move(xCoefficient*speedOfMotion, yCoefficient*speedOfMotion);
    }

    public abstract Boolean CollisionWithWall();

    public abstract Boolean CollisionWithHero();

    public abstract Boolean CollisionWithPoint();

    public abstract Boolean CollisionWithEnemy();

}
