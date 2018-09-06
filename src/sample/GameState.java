package sample;

import javafx.scene.layout.AnchorPane;

public class GameState {
    AnchorPane gamePanel;
    GameState(AnchorPane _panel)
    {
        gamePanel = _panel;
    }
    public void AddBall(Ball tmp)
    {
        gamePanel.getChildren().add(tmp.model);
    }
}
