package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.Player;
import save.Reader;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {
    private PhysicGame physicGame;
    private int clientCounter;
    private HashMap<Integer, Player> playerMap;

    public BridgeClass() throws IOException {
        physicGame = new PhysicGame();
        new Reader("output.txt", physicGame);
        clientCounter = 0;
        playerMap = new HashMap<>();
    }

    public Player getPlayer(int id) {
        return playerMap.get(id);
    }

    public void setPlayer(Player player) {

    }

    public State getObjectList(int id) {
        return new State(playerMap.get(id), physicGame.getObjectList());
    }

    public void setMotionControl(MotionControl motionControl, int id) {
        Player player = playerMap.get(id);
        player.setMotionControl(motionControl);
    }

    @Override
    public int getId() {
        clientCounter++;
        Player player = physicGame.createPlayer();
        playerMap.put(clientCounter, player);
        System.out.println(playerMap.get(clientCounter));
        return clientCounter;
    }

    public void remove(int id) {
        Player player = playerMap.get(id);
        player.kill();

    }
}
