package SongOLayin.DataPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//class that stores the table creator method for every entity

public class SongOLayinTableCreator {

    //creates table of Singers
    public void createTableOfSingers() {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("CREATE TABLE IF NOT EXISTS Singers (" +
                     "id MEDIUMINT NOT NULL AUTO_INCREMENT," +
                     "name VARCHAR(50) NOT NULL," +
                     "PRIMARY KEY (id))")) {
            pstm.execute();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //creates table of Songs
    public void createTableOfSongs() {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("CREATE TABLE IF NOT EXISTS Songs (" +
                     "id MEDIUMINT NOT NULL AUTO_INCREMENT," +
                     "id_author MEDIUMINT(50)," +
                     "name VARCHAR(70)," +
                     "type VARCHAR(20)," +
                     "PRIMARY KEY (id)," +
                     "FOREIGN KEY (id_author) REFERENCES Singers(id))")) {
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //create table of Lyrics
    public void createTableOfLyrics() {
        ProjectXConnection conn = new ProjectXConnection();
        try (Connection con = conn.getConnection();
             PreparedStatement pstm = con.prepareStatement("CREATE TABLE IF NOT EXISTS Lyrics (" +
                     "id MEDIUMINT NOT NULL AUTO_INCREMENT," +
                     "song_id MEDIUMINT(50)," +
                     "line VARCHAR(255)," +
                     "rhyme VARCHAR(50)," +
                     "length MEDIUMINT(20)," +
                     "hashcode VARCHAR(255)," +
                     "PRIMARY KEY (id)," +
                     "FOREIGN KEY (song_id) REFERENCES Songs(id))")) {
            pstm.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
