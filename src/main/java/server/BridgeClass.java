package server;

import game.PhysicGame;
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
    public ArrayList<GameObject> getObjectList() throws RemoteException {
        return physicGame.getObjectList();
    }
}
