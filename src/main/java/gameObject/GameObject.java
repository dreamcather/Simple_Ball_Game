package gameObject;

import interaction.ObjectInteractionVisitor;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.io.Serializable;
import java.util.Objects;

public abstract class GameObject implements Serializable {

    public String type;

    private final int key;

    public GameObject(int key) {
        this.key = key;
    }

    public abstract <T> T collision(ObjectInteractionVisitor<T> ballVisitor);

    public abstract void changeVector();

    public abstract void move();

    public abstract boolean isAlive();

    public abstract String toString();

    public abstract VisualInformation isVisible(Camera camera);

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameObject that = (GameObject) o;
        return key == that.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
