package SongOLayin.Service;

import SongOLayin.DataPersistence.MinglerToDB;

import java.util.HashSet;

public class RhymeService {

    // get all lyrics with the same rhyme, based on a first rhyme extracted by method getRandomID()
    public HashSet<String> getLyricsWithRhyme(){
        String randomRhyme = MinglerToDB.getInstance().getRhymeFromRandomNo(getRandomId());
        return MinglerToDB.getInstance().getAllRhymedLyrics(randomRhyme);
    }

    // get a random int between 1 and maximum number of lyrics - getNumberOfAllLyrics()
    private int getRandomId(){
        int noAllLyrics = MinglerToDB.getInstance().getNumberOfAllLyrics();
        return (int) (Math.random() * (noAllLyrics - 1 + 1) + 1);
    }

    // gets a random index
    public int getRandomIndexOfLyric(int max) {
        return (int) (Math.random() * (max - 1 + 1) + 1);
    }
}
