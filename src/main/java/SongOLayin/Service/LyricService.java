package SongOLayin.Service;

import SongOLayin.DataPersistence.LyricToDB;
import SongOLayin.AllBlueprints.FileInformation;
import SongOLayin.AllBlueprints.Lyric;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LyricService {

    private static LyricService instance = null;

    public static LyricService getInstance() {
        if (instance == null) {
            instance = new LyricService();
        }
        return instance;
    }

    public void lyricsToDB() throws IOException {
        List<Lyric> allLyrics = getAllLyricsFromAllFiles();
        HashSet<String> hashCodeOfAllLyrics = LyricToDB.getInstance().getLyricsHashCode();
        for (Lyric lyric : allLyrics) {
            if (!hashCodeOfAllLyrics.contains(lyric.getHashCode())) {
                LyricToDB.getInstance().insertLyric(lyric);
            }
        }
    }


    public List<Lyric> getAllLyricsFromAllFiles() throws IOException {
        List<List<Lyric>> all = getAllLyricsFromAFile();
        List<Lyric> allLyrics = new ArrayList<>();
        for (List<Lyric> l : all) {
            allLyrics.addAll(l);
        }
        return allLyrics;
    }

    public List<List<Lyric>> getAllLyricsFromAFile() throws IOException {

        List<List<Lyric>> allLyricsFromOneFile = new ArrayList<>();

        List<FileInformation> allFilesInformations = FileService.getInstance().allInformations();
        for (FileInformation f : allFilesInformations) {
            allLyricsFromOneFile.add(f.getLyrics());
        }
        return allLyricsFromOneFile;
    }


    public String getHashC(String text) {
        return DigestUtils.md5Hex(text);
    }

    public String verifyRhyme(String line) {

        if (line.length() > 8) {
            return lyricCutter(line.substring(line.trim().length() - 7));
        } else {
            return lyricCutter(line.trim());
        }
    }

    private String lyricCutter(String str) {

        List<String> listSpecials = new ArrayList<>();
        listSpecials.add("!");
        listSpecials.add("?");
        listSpecials.add(".");
        listSpecials.add(",");
        //listSpecials.add(" ");

        String[] arr = str.split("");
        List<String> all = new ArrayList<>(Arrays.asList(arr));
        Collections.reverse(all);
        for (String s : all) {
            if (listSpecials.contains(s)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str.substring(str.length() - 2);
    }

}



