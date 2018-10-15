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
    private HashMap<GameObject,Model> hashMap;

    VisualGame(AnchorPane anchorPane, MyPoint point, int height, int playingFieldHeight, int playingFieldWidth)
            throws MalformedURLException {
        playingField = new PlayingField(playingFieldHeight, playingFieldWidth, anchorPane);
        this.visualFactory = new VisualFactory(anchorPane);
        camera = new Camera(new MyPoint(height, height), point.getX(), 0, playingFieldHeight, 0, playingFieldWidth);
        modelsList = new ArrayList<>();
        hashMap = new HashMap<>();

    }

    private Model find(GameObject gameObject) {
        return hashMap.get(gameObject);
    }

    public void update(ArrayList<VisualInformation> inputList) {
        playingField.refresh(camera);
        Model currentModel;
        for (VisualInformation visualInformation : inputList) {
            currentModel = find(visualInformation.getGameObject());
            if (currentModel != null) {
                currentModel.setUse(true);
                currentModel.refresh(visualInformation);
            } else {
                currentModel = visualFactory.create(visualInformation);
                hashMap.put(visualInformation.getGameObject(),currentModel);
                currentModel.setUse(true);
                modelsList.add(currentModel);
            }
        }
        for (int i = 0; i < modelsList.size(); i++) {
            if (!modelsList.get(i).isUse()) {
                modelsList.get(i).hide();
                modelsList.remove(modelsList.get(i));
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