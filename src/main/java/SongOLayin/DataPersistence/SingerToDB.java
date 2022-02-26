package SongOLayin.DataPersistence;

import SongOLayin.Entities.Singer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingerToDB {

    // Static method to create instance of Singleton class
    private static SingerToDB instance = null;

    public static SingerToDB getInstance() {
        if (instance == null) {
            instance = new SingerToDB();
        }
        return instance;
    }

    //insert the singers' name
    public void insertSinger(String singers) {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("INSERT INTO Singers (name) VALUES (?)")) {
            pstm.setString(1, singers);
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //extract all the singers from the db into a list using getSinger helping method
    public List<Singer> extractSingers() {
        List<Singer> allSingers = new ArrayList<>();
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("SELECT * FROM Singers");
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                allSingers.add(getSinger(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allSingers;
    }

    //gets every song's name and author
    public Map<String, String> getSongsNameAndSinger() {
        Map<String, String> singersSongs = new HashMap<>();
        ProjectXConnection con = new ProjectXConnection();
        try (Connection conn = con.getConnection();
             PreparedStatement pstm = conn.prepareStatement("SELECT Singers.name, Songs.name FROM Singers INNER JOIN Songs ON Songs.id_author = Singers.id");
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                singersSongs.put(rs.getString("Songs.name"), rs.getString("Singers.name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return singersSongs;
    }

    //gets the number of lyrics of a singer
    public Map<String, Integer> getNumberOfLyricsOfEverySinger() {
        Map<String, Integer> authorLyrics = new HashMap<>();
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("SELECT Singers.name, COUNT(*) " +
                     "FROM Singers " +
                     "INNER JOIN Songs ON Singers.id = Songs.id_author " +
                     "INNER JOIN Lyrics ON Lyrics.song_id = Songs.id_author " +
                     "GROUP BY Singers.name"
             );
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                authorLyrics.put(rs.getString("Singers.name"), rs.getInt(2));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return authorLyrics;
    }

    //helping method that creates Singer-type entities
    private Singer getSinger(ResultSet rs) throws SQLException {
        return new Singer(rs.getInt("id"), rs.getString("name"));
    }
}
