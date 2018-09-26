package object;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class VisualFactory {
    private AnchorPane anchorPane;
    private Color enemyColor = Color.RED;
    private Color playerColor = Color.BLUE;
    private Color prizeColor = Color.GREEN;

    public VisualFactory(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Model create(GameObject object) {
        if(object.type =="E") {
            Ball ball =(Ball)object;
            return new CircleModel(ball.getPosition(), ball.getRadius(), enemyColor, anchorPane);
        }
        if(object.type =="P") {
            Ball ball =(Ball)object;
            return new CircleModel(ball.getPosition(), ball.getRadius(), playerColor, anchorPane);
        }
        if(object.type =="Pr") {
            Ball ball =(Ball)object;
            return new CircleModel(ball.getPosition(), ball.getRadius(), prizeColor, anchorPane);
        }
        if(object.type =="W") {
            Wall wall =(Wall) object;
            return new WallModel(wall.getStart(),wall.getEnd(),anchorPane);
        }
        else return null;
    }

}
