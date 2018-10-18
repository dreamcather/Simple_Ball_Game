package game;

import gameObject.GameObject;
import gameObject.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class State implements Serializable {
    Player player;
    public ArrayList<GameObject> gameObjects;

    public State(Player player, ArrayList<GameObject> gameObjects) {
        this.player = player;
        this.gameObjects = gameObjects;
    }
}
