package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    ArrayList<Enemy> enemyList;
    ArrayList<Point> pointList;
    WallColection walls;
    double xp = 100;
    double yp = 100;
    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        enemyList = new ArrayList<>();
        pointList = new ArrayList<>();
        hero = new Hero(1, -1, 1, _panel,this, keyboardSubscription);
        walls = new WallColection(4);
        walls.collection[0] = new Wall(0,100,0,0);
        walls.collection[1] = new Wall(200,0,200,200);
        walls.collection[2] = new Wall(0,0,200,0);
        walls.collection[3] = new Wall(200,200,0,200);
    }

    public void addEnemy(){
        enemyList.add(new Enemy(Math.random(),Math.random(),2, gamePanel));
    }

    public void addPoint(){
        pointList.add(new Point(0.8,1,3,gamePanel));
    }


    private void collisionWithWalls(Ball ball){
        double radToWal=0;
        for(int i=0;i< walls.collection.length;i++){
            Wall curentWall = walls.collection[i];
            double lenght;
            if((curentWall.calculateDistanceToPoint(ball.getXCenter()+ball.getxCoefficient()*ball.getSpeedOfMotion()
                   ,ball.getYCenter() +ball.getyCoefficient()*ball.getSpeedOfMotion() ))<ball.getRadius()){

                lenght = curentWall.calculateDistanceToPoint(ball.getXCenter()
                        ,ball.getYCenter());
                

                double xCoefficientLine = -ball.yCoefficient;
                double yCoefficientLine = ball.xCoefficient;
                double freeCoefficientLine =ball.yCoefficient*ball.getXCenter()-ball.xCoefficient*ball.getYCenter();

                double mainDeterminant = curentWall.xLineCoefficient*yCoefficientLine -  xCoefficientLine*curentWall.yLineCoefficient;
                double xDeterminant = xCoefficientLine*curentWall.freeLineCoefficient - curentWall.xLineCoefficient*freeCoefficientLine;
                double yDeterminant = yCoefficientLine*curentWall.freeLineCoefficient - curentWall.yLineCoefficient*freeCoefficientLine;

                double yCoordinateTouch = xDeterminant/mainDeterminant;
                double xCoordinateTouch = -yDeterminant/mainDeterminant;

                double xMiddlePoint = xCoordinateTouch+curentWall.xNormal*lenght;
                double yMiddlePoint = yCoordinateTouch + curentWall.yNormal*lenght;

                double xEndPoint = 2*xMiddlePoint-ball.getXCenter();
                double yEndPoint = 2*yMiddlePoint-ball.getYCenter();

                ball.changeVector(xEndPoint-xCoordinateTouch,yEndPoint-yCoordinateTouch);



            }

        }
    }

    private void collisionWithEnemy(Ball ball){
        for(int i =0;i < enemyList.size();i++){
            Enemy curentEnemy = enemyList.get(i);
            double lenght = Math.sqrt(Math.pow((ball.getXCenter() - curentEnemy.getXCenter()),2)+
                    Math.pow((ball.getYCenter() - curentEnemy.getYCenter()),2));
            if(lenght< (curentEnemy.getRadius() + ball.getRadius())){
                if(ball!=curentEnemy){
                    curentEnemy.accept(ball.accept(new CollisionVisitor())).collide();
                }
            }
        }
    }

    public void move(){
        collisionWithWalls(hero);
        for(int i = 0;i<enemyList.size();i++){
           collisionWithWalls(enemyList.get(i));
        }
        for(int i = 0;i<enemyList.size();i++){
            collisionWithEnemy(enemyList.get(i));
        }
        for(int i = 0;i<enemyList.size();i++){
            enemyList.get(i).move();
        }
        for(int i = 0;i<pointList.size();i++){
            collisionWithWalls(pointList.get(i));
        }
        for(int i = 0;i<pointList.size();i++){
            pointList.get(i).move();
        }
        hero.move();

    }

}
