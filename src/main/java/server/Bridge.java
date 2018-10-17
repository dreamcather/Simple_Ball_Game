package server;

import gameObject.GameObject;
import gameObject.Player;
import org.locationtech.jts.geom.Point;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {
    String NAME = "Bridge";

    Player getPlayer() throws RemoteException;

    ArrayList<GameObject> getObjectList() throws RemoteException;

}
