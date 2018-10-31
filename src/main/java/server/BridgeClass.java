package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.ClientRMIInterface;
import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.GameObject;
import gameObject.Player;
import javafx.util.Pair;
import save.Reader;

public class BridgeClass extends UnicastRemoteObject implements Bridge {
    private final PhysicGame physicGame;
    private int clientCounter;
    private final HashMap<Integer, Player> playerMap;
    private final HashMap<Integer, ClientRMIInterface> clientMap;
    private final Connection connect;
    private final ExecutorService executorService;

    private void insert(String name, int value) {
        String sql = "INSERT INTO Record(name,score) VALUES(?,?)";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Pair<String, Integer>> get10MaxRecords() {
        String sql = "SELECT Name, Score FROM Record ORDER BY Score DESC LIMIT 10";
        ArrayList<Pair<String, Integer>> recordPair = new ArrayList<>();
        Statement stmt;
        try {
            stmt = connect.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                recordPair.add(new Pair<>(resultSet.getString("Name"), resultSet.getInt("Score")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordPair;
    }

    @Override
    public void sendClient(ClientRMIInterface clientRMIInterface, Integer id) {
        clientMap.put(id, clientRMIInterface);
        sendMessageAll("Player " + id + " joined");
    }

    private void sendMessageAll(String string) {
        clientMap.forEach((k, v) -> {
            try {
                v.sendMessage(string);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    public BridgeClass(Connection connect) throws IOException {
        this.connect = connect;
        clientCounter = 0;
        playerMap = new HashMap<>();
        clientMap = new HashMap<>();
        physicGame = new PhysicGame(this);
        executorService = Executors.newCachedThreadPool();
        new Reader("output.txt", physicGame);
    }

    public Player getPlayer(int id) {
        return playerMap.get(id);
    }

    public void setMotionControl(MotionControl motionControl, int id) {
        Player player = playerMap.get(id);
        player.setMotionControl(motionControl);
    }

    @Override
    public int getId() {
        clientCounter++;
        Player player = physicGame.createPlayer();
        playerMap.put(clientCounter, player);
        return clientCounter;
    }

    public void remove(int id) {
        Player player = playerMap.get(id);
        clientMap.remove(id);
        player.kill();
        sendMessageAll("Player " + id + " left game");

    }

    @Override
    public void sendRecord(int id, String name) {
        insert(name, playerMap.get(id).getScore());

    }

    public void update(ArrayList<GameObject> objectList) {
        clientMap.forEach((k, v) -> executorService.submit(() -> {
            try {
                v.update(new State(playerMap.get(k), objectList));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }));
    }
}
