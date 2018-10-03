package visual;

import gameObject.Ball;
import gameObject.GameObject;
import gameObject.Wall;
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

    public Model create(VisualInformation visualInformation) {
        if(visualInformation.type =="E") {
            return new CircleModel(visualInformation.ballPoint,15, enemyColor, anchorPane,"E");
        }
        if(visualInformation.type =="P") {
            return new CircleModel(visualInformation.ballPoint, 15, playerColor, anchorPane,"P");
        }
        if(visualInformation.type =="Pr") {
            return new CircleModel(visualInformation.ballPoint, 15, prizeColor, anchorPane,"Pr");
        }
        if(visualInformation.type =="W") {
            return new WallModel(visualInformation.wallStart,visualInformation.wallEnd,anchorPane);
        }
        else return null;
    }

}
