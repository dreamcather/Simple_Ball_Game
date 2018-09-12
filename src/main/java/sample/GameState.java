package sample;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    ArrayList<Ball> objectList;
    WallColection walls;
    double xp = 100;
    double yp = 100;
    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        objectList =new ArrayList<>();
        hero = new Hero(-1, 0, 1,300,300, _panel,this, keyboardSubscription);
        objectList.add(hero);
        walls = new WallColection(4);
        walls.collection[0] = new Wall(new Point(0,500),new Point(0,0));
        walls.collection[1] = new Wall(new Point(500,0),new Point(500,500));
        walls.collection[2] = new Wall(new Point(500,0),new Point(0,0));
        walls.collection[3] = new Wall(new Point(500,500),new Point(0,500));
    }

    public void addEnemy(){
        objectList.add(new Enemy(Math.random(),Math.random(),3, Math.random()*500,Math.random()*500, gamePanel));
    }

    public void addEnemy(int count){
        for (int i=0;i<count;i++){
            addEnemy();
        }
    }
    public void addPrize(int count){
        for (int i=0;i<count;i++){
            addPrize();
        }
    }

    public void addPrize(){
        objectList.add(new Prize(Math.random(),Math.random(),3,Math.random()*500,Math.random()*500,gamePanel));
    }


    private void collisionWithWalls(Ball ball){
        double radToWal=0;
        for(int i=0;i< walls.collection.length;i++){
            Wall curentWall = walls.collection[i];
            double lenght;
            if((curentWall.calculateDistanceToPoint(ball.getFuturePosition())<ball.getRadius())){

                Point collisionPoint = curentWall.getLine().intersectionLine(ball.getPosition(),ball.getVector());

                Line perpendicularLine = new Line(collisionPoint,curentWall.getLine().getNormal());

                Point middlePoint = perpendicularLine.getProectionPoint(ball.getPosition());

                Vector paralVector = new Vector(ball.getPosition(),middlePoint);

                Point endPoint = paralVector.addition(middlePoint);

                ball.changeVector(new Vector(collisionPoint,endPoint));




            }

        }
    }

    private double distanceBall(Point first, Point second)
    {
        return Math.sqrt(Math.pow(first.getX() - second.getX(),2)+Math.pow(first.getY() - second.getY(),2));
    }

    private void collisionWithBall(Ball ball, int number){
        for(int i = number+1;i<objectList.size();i++){
            Ball currentBall = objectList.get(i);
            if(distanceBall(ball.getFuturePosition(),currentBall.getFuturePosition())<ball.getRadius() + currentBall.getRadius()){
                ball.accept(currentBall.accept(new CollisionVisitor())).collide();
            }
        }
    }

    public void move(){
        for(int i = 0;i<objectList.size();i++){
           collisionWithWalls(objectList.get(i));
        }
        for(int i = 0;i<objectList.size();i++){
            collisionWithBall(objectList.get(i),i);
        }
        for(int i = 0;i<objectList.size();i++){
            objectList.get(i).move();
        }
        hero.move();

    }

}
