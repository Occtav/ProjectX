package SongOLayin;

import SongOLayin.DataPersistence.SingerToDB;
import SongOLayin.DataPersistence.SongOLayinTableCreator;
import SongOLayin.Mingler.CrossedRhyme;
import SongOLayin.Mingler.PairedRhyme;
import SongOLayin.Service.*;
import java.io.IOException;
import java.sql.SQLException;

public class LyricGenerator {
    public static void main(String[] args) throws SQLException, IOException {

        // Create tables
        SongOLayinTableCreator creator = new SongOLayinTableCreator();
        creator.createTableOfSingers();
        creator.createTableOfSongs();
        creator.createTableOfLyrics();

        TypeGuesserService typeGuesserService = new TypeGuesserService();

        // Write all singers in DB
        try {
            SingerService.getInstance().persistSingersToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //introduce songs in db
        try {
            SongService.getInstance().persistSongsToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //introduce lyrics in db
        try {
            LyricService.getInstance().persistLyricsToDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get lyrics paired two by two
        PairedRhyme.getInstance().getPairedLyrics(8);

        // get lyrics with crossed rhymes
        CrossedRhyme crossedRhyme = new CrossedRhyme();
        crossedRhyme.getCrossedLyrics(8);

        System.out.println(typeGuesserService.getGuessedType(typeGuesserService.getNameOfSongsAndLyrics(FileService.getInstance().getAllInformations())));
        //       inner join
        System.out.println(SingerToDB.getInstance().getSongsNameAndSinger());
        System.out.println(SingerToDB.getInstance().getNumberOfLyricsOfEverySinger());

        LyricService lyricService = new LyricService();
        System.out.println(lyricService.verifyRhyme("pe langa plopii fara sot...!?"));
    }
}
