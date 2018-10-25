package server;

import control.MotionControl;
import game.PhysicGame;
import game.State;
import gameObject.Player;
import geometry.Line;
import save.Reader;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {
    private PhysicGame physicGame;
    private int clientCounter;
    private HashMap<Integer, Player> playerMap;
    private Connection connect;

    private void insert(int number, String name, int value) {
        String sql = "INSERT INTO Record(id,name,value) VALUES(?,?,?)";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setInt(1, number);
            pstmt.setString(2, name);
            pstmt.setInt(3, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteLast() {
        String sql = "DELETE FROM Record WHERE id = 10";

        Statement stmt = null;
        try {
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void reducePosition(int number){
        String sql = "UPDATE Record SET id = id+1 WHERE id >= ? ";

        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setInt(1,number);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void selectAll(int number,String name){
        String sql = "SELECT id, value FROM Record";

        try (
             Statement stmt  = connect.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ArrayList<Integer> arrayList = new ArrayList<Integer>();

            // loop through the result set
            while (rs.next()) {
               arrayList.add(rs.getInt("value"));
            }
            arrayList.add(number);
            arrayList.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1 ;
                }
            });
            int count= arrayList.indexOf(number);
            reducePosition(count);
            insert(count,name,number);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private boolean find(String name) throws SQLException {
        String sql = "SELECT * FROM Record WHERE name = ?";

        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return true;
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
    public void sendRecord(int id, String name) {
        reducePosition(1);
        selectAll(playerMap.get(id).getScore(),name);

    }
}
