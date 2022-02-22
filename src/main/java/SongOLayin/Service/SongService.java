package SongOLayin.Service;

import SongOLayin.DataPersistence.SingerToDB;
import SongOLayin.DataPersistence.SongToDB;
import SongOLayin.AllBlueprints.FileInformation;
import SongOLayin.AllBlueprints.Song;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SongService {

    private final File fileName = new File("C:\\Users\\ovidi\\Desktop\\Manele si manelisti.txt");


    private static SongService instance = null;

    public static SongService getInstance(){
        if(instance == null){
            instance = new SongService();
        }
        return instance;
    }

    public void songsToDB() throws IOException, SQLException {
        List<FileInformation> fileInformations = FileService.getInstance().allInformations();
        List<Song> allSongs = fileInformations.stream().map(FileInformation::getSong).collect(Collectors.toList());
        System.out.println(allSongs);
        for (Song s : allSongs) {
                verifyAndIntroduce(s.getAuthor());
                if (!verifySongName(s)) {
                    SongToDB.getInstance().writeSongToDB(s);
                }
            }

    }

    public void verifyAndIntroduce(String name) throws IOException {
        if(!SingerService.getInstance().verifySinger(name)){
            SingerToDB.getInstance().insertSinger(name);
        }
    }

    private boolean verifySongName(Song name) throws SQLException {
        boolean exists = false;
        List<String> allSongsFromDB = SongToDB.getInstance().getAllSongsName();
        if(allSongsFromDB.contains(name.getName())){
            exists = true;
        }
        return exists;
    }

}
