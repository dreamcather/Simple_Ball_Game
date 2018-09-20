package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.util.function.Consumer;

public interface KeyboardSubscription {
    void subscribeToKey(Consumer<KeyCode> consumer);
}
