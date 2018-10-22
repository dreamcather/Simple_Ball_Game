package server;

import control.MotionControl;
import game.State;
import gameObject.Player;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bridge extends Remote {
    String NAME = "Bridge";

    Player getPlayer(int id) throws RemoteException;

    void setPlayer(Player player) throws RemoteException;

    State getObjectList(int id) throws RemoteException;

    void setMotionControl(MotionControl motionControl, int id) throws RemoteException;

    int getId() throws RemoteException;

    void remove(int id) throws RemoteException;

}
