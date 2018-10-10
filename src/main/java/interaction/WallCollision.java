package interaction;

import gameObject.Ball;
import gameObject.Wall;

public class WallCollision implements Collision {
    Wall wall;
    Ball ball;

    public WallCollision(Wall wall, Ball ball) {
        this.wall = wall;
        this.ball = ball;
    }

    @Override
    public void collide() {
        ball.sumPerpendicularVector(wall.getLine().getNormal());
    }
}
