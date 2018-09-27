package gameObject;

import javafx.scene.input.KeyCode;

import java.util.function.Consumer;

public interface KeyboardSubscription {
    void subscribeToKey(Consumer<KeyCode> consumer);
}
