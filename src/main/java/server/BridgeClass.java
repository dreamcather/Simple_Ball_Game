package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.GameObject;
import gameObject.Player;
import org.locationtech.jts.geom.Point;
import save.Reader;
import visual.Camera;
import visual.visualInformation.VisualInformation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BridgeClass extends UnicastRemoteObject implements Bridge{
    PhysicGame physicGame;

    public BridgeClass() throws IOException {
        physicGame = new PhysicGame();
        Reader reader = new Reader("output.txt",physicGame);
    }

    public Player getPlayer() throws RemoteException {
        return physicGame.getPlayer();
    }
    public State getObjectList() throws RemoteException {
        return physicGame.getObjectList();
    }

    public void setMotionControl(MotionControl motionControl) throws RemoteException {
        physicGame.setMotionControl(motionControl);
    }
}
