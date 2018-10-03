package game;

import gameObject.GameObject;
import geometry.Point;
import javafx.scene.layout.AnchorPane;
import visual.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class VisualGame {
    Point position;
    int heiht;
    private VisualFactory visualFactory;
    private ArrayList<GameObject> visualObjectList;
    private ArrayList<Model> modelsList;
    PlayingField playingField;
    Camera camera;

    VisualGame(AnchorPane anchorPane,Point point,int weight, int playingFieldHeight, int playingFieldWidth) throws MalformedURLException {
        position =point;
        heiht = weight;
        playingField = new PlayingField(playingFieldHeight,playingFieldWidth,anchorPane);
        this.visualFactory = new VisualFactory(anchorPane);
        camera = new Camera(new Point(heiht,heiht),position.getX(),0,playingFieldHeight,0,playingFieldWidth);
        visualObjectList = new ArrayList<>();
        modelsList = new ArrayList<>();

    }
    private Model find(VisualInformation visualInformation){
        Model res = null;
        for(Model model:modelsList){
            if(model.isUse()==false) {
                if (model.type == visualInformation.type) {
                    res = model;
                }
            }
        }
        return res;
    }
    public void update(ArrayList<VisualInformation> inputList){
            playingField.refresh(camera);
            Model curentModel;
            for (VisualInformation visualInformation : inputList) {
                curentModel = find(visualInformation);
                if (curentModel != null) {
                    curentModel.setUse(true);
                        curentModel.refresh(visualInformation);
                } else {
                        curentModel = visualFactory.create(visualInformation);
                        curentModel.setUse(true);
                        modelsList.add(curentModel);
                }
            }
            for (int i = 0; i < modelsList.size(); i++) {
                    if (modelsList.get(i).isUse() == false) {
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