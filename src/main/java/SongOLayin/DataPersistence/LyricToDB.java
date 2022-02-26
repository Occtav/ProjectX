package SongOLayin.DataPersistence;

import SongOLayin.Entities.Lyric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


//class that establish lyric-related connectivity to database

public class LyricToDB {

    // Static method to create instance of Singleton class
    private static LyricToDB instance = null;
    public static LyricToDB getInstance() {
        if (instance == null) {
            instance = new LyricToDB();
        }
        return instance;
    }

    //insert every lyric into the database
    public void insertLyric(Lyric l) {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("INSERT INTO Lyrics (song_id, line, rhyme, length, hashcode) VALUES (?,?,?,?,?)")) {
            pstm.setInt(1, getSongId(l.getSongName()));
            pstm.setString(2, l.getLine());
            pstm.setString(3, l.getRhyme());
            pstm.setInt(4, l.getLength());
            pstm.setString(5, l.getHashCode());
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //extracts all the hashcodes from the db
    //useful when we want to check the duplicates
    public HashSet<String> getLyricsHashCode(){
        ProjectXConnection conn = new ProjectXConnection();
        HashSet<String> lyricsKeys = new HashSet<>();
        try(Connection con = conn.getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT hashcode FROM Lyrics");
        ResultSet rs = pstm.executeQuery()){
            while(rs.next()){
                lyricsKeys.add(rs.getString("hashcode"));
            }
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return lyricsKeys;
    }

    //extracts song's id using the name
    private int getSongId(String name) {
        int id = 0;
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("SELECT id FROM Songs WHERE name = ?")) {
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
