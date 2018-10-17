package client;

import gameObject.GameObject;
import gameObject.Player;
import geometry.MyPoint;
import org.locationtech.jts.geom.Point;
import server.Bridge;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
    Bridge bridge;

    public Client(Bridge bridge) {
        this.bridge = bridge;
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
}