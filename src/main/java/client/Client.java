package client;

import control.MotionControl;
import game.State;
import gameObject.Player;
import server.Bridge;
import java.rmi.RemoteException;

public class Client {
    private Bridge bridge;
    private int id;

    public Client(Bridge bridge) {
        this.bridge = bridge;
        try {
            id = bridge.getId();
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

    public State getObjectList() {
        try {
            return bridge.getObjectList(id);
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
}