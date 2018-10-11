package game;

import geometry.MyPoint;
import javafx.scene.layout.AnchorPane;
import visual.*;
import visual.models.Model;
import visual.models.VisualFactory;
import visual.visualInformation.VisualInformation;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class VisualGame {
    private VisualFactory visualFactory;
    private ArrayList<Model> modelsList;
    private PlayingField playingField;
    private Camera camera;

    VisualGame(AnchorPane anchorPane, MyPoint point, int height, int playingFieldHeight, int playingFieldWidth) throws MalformedURLException {
        playingField = new PlayingField(playingFieldHeight, playingFieldWidth, anchorPane);
        this.visualFactory = new VisualFactory(anchorPane);
        camera = new Camera(new MyPoint(height, height), point.getX(), 0, playingFieldHeight, 0, playingFieldWidth);
        modelsList = new ArrayList<>();

    }

    private Model find(VisualInformation visualInformation) {
        Model res = null;
        for (Model model : modelsList) {
            if (!model.isUse()) {
                if (model.type.equals(visualInformation.type)) {
                    res = model;
                }
            }
        }
        return res;
    }

    public void update(ArrayList<VisualInformation> inputList) {
        playingField.refresh(camera);
        Model currentModel;
        for (VisualInformation visualInformation : inputList) {
            currentModel = find(visualInformation);
            if (currentModel != null) {
                currentModel.setUse(true);
                currentModel.refresh(visualInformation);
            } else {
                currentModel = visualFactory.create(visualInformation);
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