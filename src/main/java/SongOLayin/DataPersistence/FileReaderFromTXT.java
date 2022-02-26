package SongOLayin.DataPersistence;

import SongOLayin.Models.MusicGenre;
import SongOLayin.Entities.Lyric;
import SongOLayin.Service.FileService;
import SongOLayin.Service.LyricService;
import SongOLayin.Entities.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


//This class reads files from .txt
// TODO: The existence and the placement of this class need further analysis

public class FileReaderFromTXT {

    private static final File folderName = new File("C:\\Users\\ovidi\\Desktop\\SongOLayin");


    private static FileReaderFromTXT instance = null;
    // Static method to create instance of Singleton class to make sure we have only one instance at a time
    public static FileReaderFromTXT getInstance(){
        if(instance == null){
            instance = new FileReaderFromTXT();
        }
        return instance;
    }

    // method that extracts all the lyrics from a file.txt.
    public List<Lyric> readLinesFromTxt(File file) throws IOException {
        List<Lyric> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(createLine(line, file));
            }
        }
        return lines;
    }

    public List<String> readSingerFromFile(File fileName) throws IOException {
        List<String> singers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = (br.readLine())) != null) {
                singers.add(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        System.out.println(singers);
        return singers;
    }

    //helping method that takes the name of the file which has a standard format and extracts the song's name
    //here is generated the hashcode of every lyric through getHashC
    private Lyric createLine(String line, File file){
        FileService fileService = new FileService();
        String stringFormat = fileService.getStringFormatOfFile(file);
        return new Lyric(line, fileService.getSongName(stringFormat), LyricService.getInstance().getHashCode(line));
    }

    private Song createSong(String[] line) {
        return new Song(line[0], line[1], MusicGenre.valueOf(line[2]));
    }
}
