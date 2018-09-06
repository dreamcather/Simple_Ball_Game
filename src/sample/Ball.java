package sample;

import javafx.scene.shape.Circle;

public class Ball {
    double x; //x coordinate of vector motion
    double y; //y coordinate of vector motion
    double speed; // distance ball for frame
    Circle model; // model for visualisation
    GameState game; // game state management
    Ball(double _x, double _y, double _speed, GameState _game){
        x=_x;
        y=_y;
        speed=_speed;
        model = new Circle();
        game = _game;
        game.AddBall(this);
    }
    void Move(){
        model.setCenterX(model.getCenterX()+speed*x);
        model.setCenterY(model.getCenterY()+speed*y);
    }
}
