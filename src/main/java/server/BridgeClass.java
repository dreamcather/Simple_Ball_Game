package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
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

    public State getObjectList(int id) throws RemoteException {
        return new State(playerMap.get(id),physicGame.getObjectList());
    }

    public void setMotionControl(MotionControl motionControl,int id) throws RemoteException {
        Player player = playerMap.get(id);
        player.setMotionControl(motionControl);
    }

    @Override
    public int getId() throws RemoteException {
        clientCounter++;
        Player player = physicGame.createPlayer();
        playerMap.put(clientCounter,player);
        System.out.println(playerMap.get(clientCounter));
        return clientCounter;
    }
}
