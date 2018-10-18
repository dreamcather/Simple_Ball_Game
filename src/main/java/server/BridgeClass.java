package server;

import control.MotionControl;
import game.PhysicGame;
import gameObject.GameObject;
import gameObject.Player;
import save.Reader;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge{
    PhysicGame physicGame;
    private int clientCounter;
    HashMap<Integer,Player> playerMap;

    public BridgeClass() throws IOException {
        physicGame = new PhysicGame();
        Reader reader = new Reader("output.txt",physicGame);
        clientCounter=0;
        playerMap = new HashMap<>();
    }

    public Player getPlayer(int id) throws RemoteException {
        return playerMap.get(id);
    }
    public void setPlayer(Player player) throws RemoteException {

    }

    public ArrayList<GameObject> getObjectList() throws RemoteException {
        return physicGame.getObjectList();
    }

    public void setMotionControl(MotionControl motionControl) throws RemoteException {
        physicGame.setMotionControl(motionControl);
    }

    @Override
    public int getId() throws RemoteException {
        clientCounter++;
        Player player = physicGame.addPlayer("1 0 0.3 310 50 15");
        playerMap.put(clientCounter,player);
        return clientCounter;
    }
}
