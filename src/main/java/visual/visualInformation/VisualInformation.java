package visual.visualInformation;

import gameObject.GameObject;

public abstract class VisualInformation {
    private GameObject gameObject;

    public VisualInformation(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
