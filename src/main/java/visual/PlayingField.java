package visual;


import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.MalformedURLException;

public class PlayingField {
    int height;
    int width;
    ImageView background;
    ImageView currentImage;
    PixelReader pixelReader;

    public PlayingField(int height, int width, AnchorPane anchorPane) throws MalformedURLException {
        this.height = height;
        this.width = width;
        File file = new File("src/main/resource/BrickBorder.jpg");
        String localUrl = file.toURI().toURL().toString();
        background = new ImageView(localUrl);
        background.setFitHeight(height);
        background.setFitWidth(width);
        background.setX(0);
        background.setY(0);
        pixelReader = background.getImage().getPixelReader();
        currentImage = new ImageView();
        anchorPane.getChildren().add(currentImage);
    }

    public void refresh(Camera camera){
        currentImage.setImage(new WritableImage(pixelReader,(int)camera.position.getX()-250,(int)camera.position.getY()-250,
                500,500));
    }
}
