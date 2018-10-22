package game;

import gameObject.GameObject;
import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import visual.*;
import visual.models.Model;
import visual.models.VisualFactory;
import visual.visualInformation.VisualInformation;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VisualGame {
    private VisualFactory visualFactory;
    private ArrayList<Model> modelsList;
    private PlayingField playingField;
    private Camera camera;
    private HashMap<GameObject, Model> hashMap;

    public VisualGame(AnchorPane anchorPane, MyPoint point, int width, int playingFieldHeight, int playingFieldWidth)
            throws MalformedURLException {
        playingField = new PlayingField(playingFieldHeight, playingFieldWidth, anchorPane);
        this.visualFactory = new VisualFactory(anchorPane);
        camera = new Camera(new MyPoint(width, width), point.getX(), 0, playingFieldHeight, 0, playingFieldWidth);
        modelsList = new ArrayList<>();
        hashMap = new HashMap<>();

    }

    private Model find(GameObject gameObject) {
        return hashMap.get(gameObject);
    }

    public void update(ArrayList<GameObject> inputList) {
        playingField.refresh(camera);
        Model currentModel;
        for (GameObject gameObject : inputList) {
            VisualInformation visualInformation = gameObject.isVisible(camera);
            if (visualInformation != null) {
                currentModel = find(gameObject);
                if (currentModel != null) {
                    currentModel.setUse(true);
                    currentModel.refresh(visualInformation);
                } else {
                    currentModel = visualFactory.create(visualInformation);
                    hashMap.put(gameObject, currentModel);
                    currentModel.setUse(true);
                    modelsList.add(currentModel);
                }
            }

        }
        for (Model aModelsList : modelsList) {
            if (!aModelsList.isUse()) {
                aModelsList.hide();
            }

        }
        for (Model model : modelsList) {
            model.setUse(false);
        }
    }

    public Camera getCamera() {
        return camera;
    }
}