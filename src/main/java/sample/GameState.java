package sample;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    ArrayList<Ball> objectList;
    WallColection walls;

    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        objectList =new ArrayList<>();
        hero = new Hero(-1, 0, 1,100,100, _panel, keyboardSubscription);
        objectList.add(hero);
        walls = new WallColection(8);
        walls.collection[0] = new Wall(new Point(0,500),new Point(0,0));
        walls.collection[1] = new Wall(new Point(500,0),new Point(500,500));
        walls.collection[2] = new Wall(new Point(500,0),new Point(0,0));
        walls.collection[3] = new Wall(new Point(500,500),new Point(0,500));
        walls.collection[4] = new Wall(new Point(250,200),new Point(200,250));
        walls.collection[5] = new Wall(new Point(200,250),new Point(250,300));
        walls.collection[6] = new Wall(new Point(250,300),new Point(300,250));
        walls.collection[7] = new Wall(new Point(300,250),new Point(250,200));
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
        for(int i=0;i< walls.collection.length;i++){
            Wall curentWall = walls.collection[i];
            if(((curentWall.calculateDistanceToPoint(ball.getFuturePosition())<ball.getRadius()))&&
            curentWall.isBetween(ball.getFuturePosition())){

                Point collisionPoint = curentWall.getLine().intersectionLine(ball.getPosition(),ball.getVector());

                Line perpendicularLine = new Line(collisionPoint,curentWall.getLine().getNormal());

                Point middlePoint = perpendicularLine.getProectionPoint(ball.getPosition());

                Vector paralelVector = new Vector(ball.getPosition(),middlePoint);

                Point endPoint = paralelVector.addition(middlePoint);

                ball.changeVector(new Vector(collisionPoint,endPoint));

            }

        }
    }

    private double distanceBall(Point first, Point second)
    {
        return Math.sqrt(Math.pow(first.getX() - second.getX(),2)+Math.pow(first.getY() - second.getY(),2));
    }

    private void clear(){
        for(int i =0; i<objectList.size();i++){
            if(objectList.get(i).isAlive()==false){
                objectList.get(i).gameModel.hide(gamePanel);;
                objectList.remove(i);
            }
        }
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
        clear();
        for(int i = 0;i<objectList.size();i++){
            objectList.get(i).move();
        }
        hero.move();

    }

}
