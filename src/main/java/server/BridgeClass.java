package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.Player;
import javafx.util.Pair;
import save.Reader;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {
    private final PhysicGame physicGame;
    private int clientCounter;
    private HashMap<Integer, Player> playerMap;
    private final Connection connect;

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
        ArrayList<Pair<String,Integer>> recordPair = new ArrayList<>();
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

    public BridgeClass(Connection connect) throws IOException {
        this.connect = connect;
        physicGame = new PhysicGame();
        new Reader("output.txt", physicGame);
        clientCounter = 0;
        playerMap = new HashMap<>();
    }

    public Player getPlayer(int id) {
        return playerMap.get(id);
    }

    public State getObjectList(int id) {
        return new State(playerMap.get(id), physicGame.getObjectList());
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
        player.kill();

    }

    @Override
    public void sendRecord(int id, String name) {
        insert(name, playerMap.get(id).getScore());

    }
}
