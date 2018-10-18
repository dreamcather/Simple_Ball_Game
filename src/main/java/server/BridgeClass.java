package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.GameObject;
import gameObject.Player;
import save.Reader;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

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

    public Player getPlayer() throws RemoteException {
        return physicGame.getPlayer();
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
        return clientCounter;
    }
}
