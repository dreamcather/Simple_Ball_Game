package visual.visualInformation;

import gameObject.GameObject;
import visual.models.Model;

public abstract class VisualInformation {
    private GameObject gameObject;

    public VisualInformation(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
