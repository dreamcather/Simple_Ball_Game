package client;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import control.MotionControl;
import game.State;
import gameObject.Player;
import javafx.util.Pair;
import server.Bridge;

public class Client {
    private final Bridge bridge;
    private int id;
    transient private ClientGame clientGame;
    private ClientRMIInterface clientRMIInterface;

    public Client(Bridge bridge) {
        this.bridge = bridge;
        try {
            id = bridge.getId();
            clientRMIInterface = new ClientRMIClass(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        try {
            return bridge.getPlayer(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove() {
        try {
            bridge.remove(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendMotion(MotionControl motionControl) {
        try {
            bridge.setMotionControl(motionControl, id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRecord(String name) {
        try {
            bridge.sendRecord(id, name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendMe() {
        try {
            bridge.sendClient(clientRMIInterface, id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pair<String, Integer>> get10MaxRecords() {
        try {
            return bridge.get10MaxRecords();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setControl(ClientGame clientGame) {
        this.clientGame = clientGame;
    }

    public void sendMessage(String string) {
        clientGame.printMessage(string);
    }

    public void exit() {
        try {
            UnicastRemoteObject.unexportObject(clientRMIInterface, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

    public void update(State state) {
        clientGame.update(state);
    }
}