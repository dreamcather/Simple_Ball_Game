package game;

import geometry.Point;
import interaction.MotionControl;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import gameObject.*;
import visual.Camera;
import visual.VisualBall;
import visual.VisualFactory;

import java.util.ArrayList;

public class Game {
    private AnchorPane gamePanel;
    private Player hero;
    private Label score;
    private Label lifeCounter;
    private int countPoint;
    private PhysicGame physicGame;
    private ArrayList<VisualBall> visualObject;
    private Factory factory;
    private int prizeCount;
    MotionControl motionControl;
    AnimationTimer animationTimer;
    boolean active;
    VisualGame visualGame;
    VisualFactory visualFactory;
    Camera camera;

    public Game(AnchorPane _panel) {
        gamePanel = _panel;
        factory = new Factory(gamePanel);
        visualObject = new ArrayList<>();
        physicGame = new PhysicGame();
        score = new Label("Score");
        score.setLayoutX(550);
        score.setLayoutY(50);
        gamePanel.getChildren().add(score);
        countPoint = 0;
        lifeCounter = new Label("Life");
        gamePanel.getChildren().add(lifeCounter);
        lifeCounter.setLayoutX(550);
        lifeCounter.setLayoutY(70);
        prizeCount = 0;
        camera = new Camera(new Point(0,0));
        motionControl =new MotionControl();
        active =false;
        visualFactory = new
                VisualFactory(gamePanel);
        visualGame = new VisualGame(visualFactory,camera);
        animationTimer =new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

    }

    public void addWall(Point start, Point end) {
        physicGame.addWall(start, end);
    }

    public void addHero(Player hero) {
        this.hero = hero;
        physicGame.addBall(hero);
        //visualObject.add(factory.createPlayer(hero));
    }

    private void createPrize() {
        Prize prize = new Prize(Math.random(),
                                Math.random(),
                                Math.random() * 5,
                                Math.random() * 500,
                                Math.random() * 500,
                                15);
        physicGame.addBall(prize);
    }

    public void addEnemy(Ball enemy) {
        physicGame.addBall(enemy);

    }

    public void addPrize(Ball prize) {
        physicGame.addBall(prize);
        prizeCount++;
    }

    public void mouseClick(MouseEvent event) {
        motionControl = new MotionControl(event);
        physicGame.setMotionControl(motionControl);
    }

    public void click(KeyCode keyCode){
        if(keyCode==KeyCode.Q)
        camera.setPosition(new Point(camera.getPosition().getX()+10,camera.getPosition().getY()+10));
        if(keyCode==KeyCode.A)
            camera.setPosition(new Point(camera.getPosition().getX()-10,camera.getPosition().getY()-10));
    }

    public void start(){
        animationTimer.start();
        active =true;
    }
    public void stop(){
        animationTimer.stop();
        active =false;
    }

    public void update() {
        if (hero.getLifeCount() > -10) {
            if (hero.getScore() != countPoint) {
                score.setText("Score " + hero.getScore());
                countPoint = hero.getScore();
                prizeCount--;
            }
            lifeCounter.setText("Life " + hero.getLifeCount());

            for (VisualBall currentObject : visualObject) {
                currentObject.update();
            }


            if (prizeCount == 0) {
                createPrize();
                prizeCount++;
            }
        }
        visualGame.setObject(physicGame.getObjectList());
        visualGame.update();
    }

    public boolean isActive() {
        return active;
    }

}
