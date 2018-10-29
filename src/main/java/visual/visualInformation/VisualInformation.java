package visual.visualInformation;

import gameObject.GameObject;

public abstract class VisualInformation {
    private final GameObject gameObject;

    public VisualInformation(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
