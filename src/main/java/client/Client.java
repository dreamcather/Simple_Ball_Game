package client;

import control.MotionControl;
import game.State;
import gameObject.Player;
import javafx.util.Pair;
import server.Bridge;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client implements Serializable {
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

    public State getObjectList() throws RemoteException {
        return bridge.getObjectList(id);
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

    public void sendMe(){
        try {
            bridge.sendClient(clientRMIInterface,id);
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

    public void setControl(ClientGame clientGame){
        this.clientGame = clientGame;
    }

    public void sendMessage(String string) {
        clientGame.printMessage(string);
    }
}