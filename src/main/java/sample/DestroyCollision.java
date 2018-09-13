package sample;

public class DestroyCollision extends Collision {
    Hero main;
    Prize pray;

    public DestroyCollision(Hero main, Prize pray) {
        this.main = main;
        this.pray = pray;
    }

    @Override
    public void collide() {
        pray.eating();
    }
}
