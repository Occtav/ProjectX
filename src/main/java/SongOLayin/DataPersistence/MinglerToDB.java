package SongOLayin.DataPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//this class has methods used to generate rhymed lyrics. basically gets all the infos needed within the process

public class MinglerToDB {


    // Static method to create instance of Singleton class
    private static MinglerToDB instance = null;
    public static MinglerToDB getInstance(){
        if(instance == null){
            instance = new MinglerToDB();
        }
        return instance;
    }

    // gets the total number of lyrics existing into the database
    public int getNumberOfAllLyrics() {
        int id = 0;
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("SELECT COUNT(*) FROM Lyrics");
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    //gets a rhyme (defined as the last two characters of a lyric) from the specified index
    public String getRhymeFromRandomNo(int no){
        String rhyme = null;
        ProjectXConnection conn = new ProjectXConnection();
        try(Connection con = conn.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT rhyme FROM Lyrics WHERE id = ?")){
            pstm.setInt(1, no);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                rhyme = rs.getString("rhyme");
            }
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return rhyme;
    }

    //gets all the lyrics with the specified rhyme
    public HashSet<String> getAllRhymedLyrics(String rhyme){
        HashSet<String> allLyrics = new HashSet<>();
        ProjectXConnection conn = new ProjectXConnection();
        try(Connection con = conn.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT line FROM Lyrics WHERE rhyme = ?")){
            pstm.setString(1, rhyme);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                allLyrics.add(rs.getString("line"));
            }
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return allLyrics;
    }
}
