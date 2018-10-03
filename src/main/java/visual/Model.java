package visual;

import javafx.scene.layout.AnchorPane;

public abstract class Model {
    protected AnchorPane anchorPane;

    public abstract void refresh(VisualInformation visualInformation);

    public abstract void hide();


}
