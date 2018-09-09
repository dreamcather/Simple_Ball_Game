package sample;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class GameState {
    AnchorPane gamePanel;
    Hero hero;
    ArrayList<Enemy> enemyList;
    WallColection walls;
    GameState(AnchorPane _panel, KeyboardSubscription keyboardSubscription)
    {
        gamePanel = _panel;
        hero = new Hero(-1, 0, 1, _panel,this, keyboardSubscription);
        walls = new WallColection(1);
        walls.collection[0] = new Wall(0,100,0,0);
    }

    public void addEnemy(){
        enemyList.add(new Enemy(1,1,5, gamePanel));
    }


    private void collisionWithWalls(Ball ball){
        double radToWal=0;
        for(int i=0;i< walls.collection.length;i++){
            Wall curentWall = walls.collection[i];
            double lenght;
            if((lenght=curentWall.calculateDistanceToPoint(ball.getXCenter()+ball.getxCoefficient()*ball.getSpeedOfMotion()
                   ,ball.getYCenter() +ball.getyCoefficient()*ball.getSpeedOfMotion() ))<ball.getRadius()){

                double xVector = curentWall.xNormal*lenght;
                double yVector = curentWall.yNormal*lenght;

                double xCoefficientLine = -ball.yCoefficient;
                double yCoefficientLine = ball.xCoefficient;
                double freeCoefficientLine =ball.yCoefficient*ball.getXCenter()-ball.xCoefficient*ball.getYCenter();

                double mainDeterminant = curentWall.xLineCoefficient*yCoefficientLine -  xCoefficientLine*curentWall.yLineCoefficient;
                double xDeterminant = xCoefficientLine*curentWall.freeLineCoefficient - curentWall.xLineCoefficient*freeCoefficientLine;
                double yDeterminant = yCoefficientLine*curentWall.freeLineCoefficient - curentWall.yLineCoefficient*freeCoefficientLine;

                double yCoordinateTouch = xDeterminant/mainDeterminant;
                double xCoordinateTouch = yDeterminant/mainDeterminant;

                double lenghtLine = Math.sqrt(Math.pow((xCoordinateTouch-ball.getXCenter()),2) +
                Math.pow((yCoordinateTouch-ball.getYCenter()),2));

                double resXVector = 2*xVector + ball.xCoefficient*lenghtLine;
                double resYVector = 2*yVector + ball.yCoefficient*lenghtLine;

                ball.changeVector(resXVector,resYVector);



            }

        }
    }

    public void move(){
        collisionWithWalls(hero);
        hero.move();

    }

}
