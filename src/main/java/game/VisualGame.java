package game;

import object.CircleModel;
import object.GameObject;
import object.Model;
import object.VisualFactory;

import java.util.ArrayList;

public class VisualGame {
    private VisualFactory visualFactory;
    private ArrayList<GameObject> visualObjectList;
    private ArrayList<Model> modelsList;

    VisualGame(VisualFactory visualFactory){
        this.visualFactory = visualFactory;
        visualObjectList = new ArrayList<>();
        modelsList = new ArrayList<>();
    }

    public void setObject(ArrayList<GameObject> inputList){
        for(int i=0;i<modelsList.size();i++){
            if(inputList.indexOf(modelsList.get(i))==-1){
                visualObjectList.remove(i);
                modelsList.get(i).hide();
                modelsList.remove(i);
            }
        }
        for(int i=0;i<inputList.size();i++) {
            if(visualObjectList.indexOf(inputList.get(i))==-1){
                visualObjectList.add(inputList.get(i));
                modelsList.add(visualFactory.create(inputList.get(i)));
            }
        }

    }

    public void update(){

    }
}