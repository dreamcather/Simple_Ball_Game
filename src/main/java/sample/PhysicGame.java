package sample;

import java.util.ArrayList;

public class PhysicGame {
    ArrayList<Ball> objectList;
    WallColection walls;

    public PhysicGame() {
        objectList = new ArrayList<>();
        walls = new WallColection();
    }

    public void addBall(Ball ball){
        objectList.add(ball);
    }

    public void addWall(Point start, Point end){
        walls.collection.add(new Wall(start,end));
    }

    private double distanceBall(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) + Math.pow(first.getY() - second.getY(), 2));
    }

    private void collisionWithWalls(Ball ball) {
        for (int i = 0; i < walls.collection.size(); i++) {
            Wall curentWall = (Wall) walls.collection.elementAt(i);
            if (curentWall.isCollisionWithBall(ball)) {
                //if(curentWall.getLine().getSign(ball.getPosition())>=0)
                    ball.addPerpendicularVector(curentWall.getLine().getNormal());

//                Point collisionPoint = curentWall.getLine().intersectionLine(ball.getPosition(), ball.getVector());
//
//                Line perpendicularLine = new Line(collisionPoint, curentWall.getLine().getNormal());
//
//                Point middlePoint = perpendicularLine.getProectionPoint(ball.getPosition());
//
//                Vector paralelVector = new Vector(ball.getPosition(), middlePoint);
//
//               Point endPoint = paralelVector.addition(middlePoint);
//
//                ball.changeVector(new Vector(collisionPoint, endPoint));

            }

        }
        for(Ball curentBall:objectList){
            curentBall.update();
        }
    }

    private void collisionWithBall(Ball mainBall, int number) {
        for (int i = number + 1; i < objectList.size(); i++) {
            Ball currentBall = objectList.get(i);
            if (distanceBall(mainBall.getPosition(), currentBall.getPosition()) < mainBall.getRadius()
                    + currentBall.getRadius()) {
                mainBall.accept(currentBall.accept(new CollisionVisitor())).collide();
            }
        }
    }

    private void clear() {
        for (int i = 0; i < objectList.size(); i++) {
            if (objectList.get(i).isAlive() == false) {
                objectList.remove(i);
            }
        }
    }

    public void move() {
        for (Ball curentObject : objectList) {
            collisionWithWalls(curentObject);
        }

        for (int i = 0; i < objectList.size(); i++) {
            collisionWithBall(objectList.get(i), i);
        }
        clear();
        for (Ball curentObject : objectList) {
            curentObject.move();
        }
    }
}
