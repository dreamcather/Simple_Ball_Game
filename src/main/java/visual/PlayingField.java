package visual;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.MalformedURLException;

public class PlayingField {
    private ImageView background;
    private ImageView currentImage;
    private PixelReader pixelReader;

    public PlayingField(int height, int width, AnchorPane anchorPane) throws MalformedURLException {
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

    public void refresh(Camera camera) {
        background.setX(camera.getOffset());
        background.setY(camera.getOffset());
        currentImage.setX(camera.getOffset());
        currentImage.setY(camera.getOffset());
        currentImage.setImage(new WritableImage(pixelReader,
                                                (int) camera.getPosition().getX() - camera.getSize(),
                                                (int) camera.getPosition().getY() - camera.getSize(),
                                                2 * camera.getSize(),
                                                2 * camera.getSize()));
    }
}
