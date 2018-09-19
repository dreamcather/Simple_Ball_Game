package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Factory {
    AnchorPane anchorPane;
    Color enemyColor = Color.RED;
    Color playerColor = Color.BLUE;
    Color prizeColor = Color.GREEN;

    public Factory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public VisualBall createEnemy(Ball ball){
        return new VisualBall(ball,new CircleModel(ball.xCoordinate,ball.yCoordinate,ball.getRadius()-4,enemyColor,anchorPane));
    }
    public VisualBall createPrize(Ball ball){
        return new VisualBall(ball,new CircleModel(ball.xCoordinate,ball.yCoordinate,ball.getRadius()-4,prizeColor,anchorPane));
    }
    public VisualBall createPlayer(Hero ball){
        return new VisualBall(ball,new CircleModel(ball.xCoordinate,ball.yCoordinate,ball.getRadius()-4,playerColor,anchorPane));
    }
}
