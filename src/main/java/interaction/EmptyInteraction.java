package interaction;

public class EmptyInteraction implements Interaction {
    @Override
    public boolean detect() {
        return false;
    }

    @Override
    public void collision() {

    }
}
