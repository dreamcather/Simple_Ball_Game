package client;

import control.MotionControl;
import game.State;
import gameObject.GameObject;
import gameObject.Player;
import server.Bridge;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
    Bridge bridge;
    int id;

    public Client(Bridge bridge) {
        this.bridge = bridge;
        try {
            id = bridge.getId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer(){
        try {
            return bridge.getPlayer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<GameObject> getObjectList() {
        try {
            return bridge.getObjectList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMotion(MotionControl motionControl){
        try {
            bridge.setMotionControl(motionControl);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}