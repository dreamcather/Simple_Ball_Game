package game;

import gameObject.GameObject;
import gameObject.Player;

import java.util.ArrayList;

public class State {
    Player player;
    ArrayList<GameObject> gameObjects;

    public State(Player player, ArrayList<GameObject> gameObjects) {
        this.player = player;
        this.gameObjects = gameObjects;
    }
}
