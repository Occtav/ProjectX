package SongOLayin.Service;

import SongOLayin.DataPersistence.SingerToDB;
import SongOLayin.Models.FileInformation;
import SongOLayin.Entities.Singer;
import SongOLayin.Entities.Song;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SingerService {

    static File fileName = new File("C:\\Users\\ovidi\\Desktop\\Manelisti.txt");

    private static SingerService instance = null;

    public static SingerService getInstance(){
        if(instance == null){
            instance = new SingerService();
        }
        return instance;
    }

    public void persistSingersToDB() throws IOException {
        List<FileInformation> fileInformations = FileService.getInstance().getAllInformations();
        List<String> singers = fileInformations.stream()
                .map(FileInformation::getAuthorName)
                .collect(Collectors.toList());

        for(String m : singers){
            if(!verifySinger(m)) {
                SingerToDB.getInstance().insertSinger(m);
            }
        }
    }

    public boolean verifySinger(String s) throws IOException {
        boolean exists = false;
        List<Singer> allSingers = SingerToDB.getInstance().extractSingers();
        List<String> namesOfSingers = allSingers.stream()
                .map(Singer::getName)
                .collect(Collectors.toList());

            if(namesOfSingers.contains(s)){
                exists = true;
            }

        return exists;
    }

    public int getIdSinger(Song manea){
        int id = 0;
        for(Singer m : SingerToDB.getInstance().extractSingers()){
            if(m.getName().equalsIgnoreCase(manea.getAuthor())){
                id = m.getId();
            }
        }

        return id;
    }
}
