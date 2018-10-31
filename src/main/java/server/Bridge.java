package server;

import client.ClientRMIInterface;
import control.MotionControl;
import game.State;
import gameObject.Player;
import javafx.util.Pair;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {

    Player getPlayer(int id) throws RemoteException;

    State getObjectList(int id) throws RemoteException;

    void setMotionControl(MotionControl motionControl, int id) throws RemoteException;

    int getId() throws RemoteException;

    void remove(int id) throws RemoteException;

    void sendRecord(int id,String name) throws RemoteException;

    ArrayList<Pair<String, Integer>> get10MaxRecords() throws RemoteException;

    void sendClient(ClientRMIInterface clientRMIInterface, Integer id) throws RemoteException;

}
