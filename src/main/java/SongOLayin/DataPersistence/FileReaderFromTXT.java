package SongOLayin.DataPersistence;

import SongOLayin.AllBlueprints.MusicGenre;
import SongOLayin.AllBlueprints.Lyric;
import SongOLayin.Service.FileService;
import SongOLayin.Service.LyricService;
import SongOLayin.AllBlueprints.Song;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

//    public List<File> getAllFiles(File folder){
//        List<File> allFiles = new ArrayList<>();
//        try (Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\ovidi\\Desktop\\SongOLayin"))) {
//
//            allFiles = paths.filter(Files::isRegularFile)
//                    .map(Path::toFile)
//                    .collect(Collectors.toList());
//
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        return allFiles;
//    }

//    public List<String> getAllFilesString(){
//        List<File> files = getAllFiles(folderName);
//        List<String> allFilesString = files.stream().map(File::toString).collect(Collectors.toList());
//        List<String> all = allFilesString.stream().map(s -> s.replace("C:\\Users\\ovidi\\Desktop\\SongOLayin\\","")).collect(Collectors.toList());
//        return all.stream().map(s -> s.replace(".txt","")).collect(Collectors.toList());
//    }

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


    //helping method that takes the name of the file which has a standard format and extracts the song's name
    //here is generated the hashcode of every lyric through getHashC
    private Lyric createLine(String line, File file){
        FileService fileService = new FileService();
        String stringFormat = fileService.stringFormatOfFile(file);
        return new Lyric(line, fileService.getSongName(stringFormat), LyricService.getInstance().getHashC(line));
    }






//-----------------------------------------------------------------
//    public List<Song> readSongFromFile(File fileName) {
//        List<Song> songs = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String lyric;
//            while ((lyric = br.readLine()) != null) {
//                String[] lyrics = lyric.split("\\s*,\\s*");
//                songs.add(createSong(lyrics));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return songs;
//    }

    private Song createSong(String[] line) {
        return new Song(line[0], line[1], MusicGenre.valueOf(line[2]));
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
}
