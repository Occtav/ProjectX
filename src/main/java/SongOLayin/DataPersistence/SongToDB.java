package SongOLayin.DataPersistence;

import SongOLayin.Entities.Song;
import SongOLayin.Service.SingerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//establish song-related connectivity to db

public class SongToDB {

    private static SongToDB instance = null;

    public static SongToDB getInstance() {
        if (instance == null) {
            instance = new SongToDB();
        }
        return instance;
    }

    //writes every song into the database
    public void writeSongToDB(Song song) {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("INSERT INTO Songs (id_author, name, type) VALUES (?,?,?)")) {
            pstm.setInt(1, SingerService.getInstance().getIdSinger(song));
            pstm.setString(2, song.getName());
            pstm.setString(3, String.valueOf(song.getType()));
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //extracts all songs' name from db using getSongName helping method
    public List<String> getAllSongsName() throws SQLException {
        List<String> allSongs = new ArrayList<>();
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("SELECT * FROM Songs");
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                allSongs.add(getSongName(rs));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allSongs;
    }

    //helping method that offers song's name
    private String getSongName(ResultSet rs) throws SQLException {
        return rs.getString("name");
    }
}
