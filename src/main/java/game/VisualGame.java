package game;

import gameObject.GameObject;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import visual.*;

import java.util.ArrayList;

public class VisualGame {
    private VisualFactory visualFactory;
    private ArrayList<GameObject> visualObjectList;
    private ArrayList<Model> modelsList;
    PlayingField playingField;
    Camera camera;

    VisualGame(VisualFactory visualFactory, Camera camera, PlayingField playingField){
        this.visualFactory = visualFactory;
        visualObjectList = new ArrayList<>();
        modelsList = new ArrayList<>();
        this.playingField =playingField;
        this.camera =camera;
    }

    public void setObject(ArrayList<GameObject> inputList){
        for(int i=0;i<modelsList.size();i++){
            if(inputList.indexOf(visualObjectList.get(i))==-1){
                visualObjectList.remove(i);
                modelsList.get(i).hide();
                modelsList.remove(i);
            }
        }
        for(int i=0;i<inputList.size();i++) {
            if(visualObjectList.indexOf(inputList.get(i))==-1){
                visualObjectList.add(inputList.get(i));
                modelsList.add(visualFactory.create(inputList.get(i),camera));
            }
        }

    }

    public void update(){
        playingField.refresh(camera);
        for(int i=0;i< modelsList.size();i++){
            Model model = modelsList.get(i);
            VisualInformation visualInformation = new VisualInformation(visualObjectList.get(i),camera);
            model.refresh(visualInformation,camera);
        }

    }
}