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

    }
}
