package client;

import control.MotionControl;
import game.State;
import gameObject.Player;
import javafx.util.Pair;
import server.Bridge;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
            //e.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }

    public void remove() {
        try {
            bridge.remove(id);
        } catch (RemoteException e) {
            //e.printStackTrace();
            System.out.println("Error");
        }
    }

    public void sendMotion(MotionControl motionControl) {
        try {
            bridge.setMotionControl(motionControl, id);
        } catch (RemoteException e) {
            //e.printStackTrace();
            System.out.println("Error");
        }
    }

    public void sendRecord(String name) {
        try {
            bridge.sendRecord(id,name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pair<String,Integer>> get10MaxRecords(){
        try {
            return bridge.get10MaxRecords();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}