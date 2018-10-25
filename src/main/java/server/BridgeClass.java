package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.Player;
import save.Reader;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {
    private PhysicGame physicGame;
    private int clientCounter;
    private HashMap<Integer, Player> playerMap;
    private Connection connect;

    public void insert(String name, int value) {
        String sql = "INSERT INTO Record(name,value) VALUES(?,?)";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void selectAll(){
        String sql = "SELECT name, value FROM Record";

        try (
             Statement stmt  = connect.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" +
                        rs.getInt("value") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean find(String name){
        String sql = "SELECT * FROM Record WHERE name = '"+name+"'";

        try (
                Statement stmt  = connect.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
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

    public void setPlayer(Player player) {

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
    public void sendRecord(int id,String name) throws RemoteException {
        if(!find(name)){
            insert(name,playerMap.get(id).getScore());
            selectAll();
        }

    }
}
