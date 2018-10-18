package server;

import control.MotionControl;
import game.State;
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

    void setPlayer(Player player) throws RemoteException;

    ArrayList<GameObject> getObjectList() throws RemoteException;

    void setMotionControl(MotionControl motionControl) throws RemoteException;

    int getId() throws RemoteException;

}
