package SongOLayin.Service;

import SongOLayin.DataPersistence.FileReaderFromTXT;
import SongOLayin.AllBlueprints.FileInformation;
import SongOLayin.AllBlueprints.MusicGenre;
import SongOLayin.AllBlueprints.Song;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    private final String sectionSeparator = "__";
    private final String wordSeparator = "-";
    private static final File folderName = new File("C:\\Users\\ovidi\\Desktop\\SongOLayin");

    private static FileService instance = null;

    public static FileService getInstance(){
        if(instance == null){
            instance = new FileService();
        }
        return instance;
    }

    public List<FileInformation> allInformations() throws IOException {

        List<File> allFiles = getAllFiles(folderName);
        List<FileInformation> allFileInformations = new ArrayList<>();
        for(File f : allFiles){
            allFileInformations.add(fileInformation(f));
        }
        return allFileInformations;
    }

    public FileInformation fileInformation(File file) throws IOException {

        String name = stringFormatOfFile(file);
        return new FileInformation(getAuthorName(name), getSong(name), FileReaderFromTXT.getInstance().readLinesFromTxt(file));
    }

    public String stringFormatOfFile(File file){
        String fileTxt = file.toString();
        String file1 = fileTxt.replace("C:\\Users\\ovidi\\Desktop\\SongOLayin\\", "");
        return file1.replace(".txt", "");
    }

    public String getAuthorName(String string){
        String[] name = string.split(sectionSeparator);
        return parseWord(name[0]);
    }

    public String getSongName(String string){
        String[] songName =string.split(sectionSeparator);
        return parseWord(songName[1]);
    }

    private Song getSong(String s){
        String[] name = s.split(sectionSeparator);
        return new Song(parseWord(name[0]), parseWord(name[1]), MusicGenre.valueOf(parseWord(name[2])));
    }





    private String parseWord(String s) {

        String[] words = s.split(wordSeparator);
        return String.join(" ", words);
    }

    public List<File> getAllFiles(File folder){
        List<File> allFiles = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\ovidi\\Desktop\\SongOLayin"))) {

            allFiles = paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return allFiles;
    }

}
