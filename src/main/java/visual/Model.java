package visual;

import javafx.scene.layout.AnchorPane;

public abstract class Model {
    protected AnchorPane anchorPane;
    private boolean isUse;
    public String type;

    public Model() {
        isUse = false;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public abstract void refresh(VisualInformation visualInformation);

    public abstract void hide();


}
