package object;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Factory {
    private AnchorPane anchorPane;
    private Color enemyColor = Color.RED;
    private Color playerColor = Color.BLUE;
    private Color prizeColor = Color.GREEN;

    public Factory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public VisualBall createEnemy(Ball ball) {
        return new VisualBall(ball, new CircleModel(ball.getPosition(), ball.getRadius(), enemyColor, anchorPane));
    }

    public VisualBall createPrize(Ball ball) {
        return new VisualBall(ball, new CircleModel(ball.getPosition(), ball.getRadius(), prizeColor, anchorPane));
    }

    public VisualBall createPlayer(Player ball) {
        return new VisualBall(ball, new CircleModel(ball.getPosition(), ball.getRadius(), playerColor, anchorPane));
    }
}
