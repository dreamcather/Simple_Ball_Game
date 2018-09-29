package game;

import gameObject.GameObject;
import visual.Model;
import visual.VisualFactory;
import visual.Camera;
import visual.VisualInformation;

import java.util.ArrayList;

public class VisualGame {
    private VisualFactory visualFactory;
    private ArrayList<GameObject> visualObjectList;
    private ArrayList<Model> modelsList;
    Camera camera;

    VisualGame(VisualFactory visualFactory,Camera camera){
        this.visualFactory = visualFactory;
        visualObjectList = new ArrayList<>();
        modelsList = new ArrayList<>();
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
        for(int i=0;i< modelsList.size();i++){
            Model model = modelsList.get(i);
            VisualInformation visualInformation = new VisualInformation(visualObjectList.get(i),camera);
            model.refresh(visualInformation,camera);
        }

    }
}