package SongOLayin.Mingler;

import SongOLayin.Service.RhymeService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PairedRhyme {

    private static PairedRhyme instance = null;

    public static PairedRhyme getInstance() {
        if (instance == null) {
            instance = new PairedRhyme();
        }
        return instance;
    }


    public void getPairedLyrics(int noOfLyrics) {

        RhymeService rhymeService = new RhymeService();

        HashSet<String> setNo1 = rhymeService.getLyricsWithRhyme();
        HashSet<String> setNo2 = rhymeService.getLyricsWithRhyme();
        List<String> listNo1 = new ArrayList<>(setNo1);
        List<String> listNo2 = new ArrayList<>(setNo2);

        int k = 0;
        while (k < noOfLyrics) {

            int i = 0;
            while (i < 2) {
                int size = listNo1.size();
                System.out.println(listNo1.get(getRandomIndexOfLyric(size) - 1));
                i++;
                k++;
            }
            if (i == 2) {
                i = 0;
            }
            int j = 0;
            while (j < 2) {
                int sizeList2 = listNo2.size();
                System.out.println(listNo2.get(getRandomIndexOfLyric(sizeList2) - 1));
                j++;
                k++;
            }
            if (j == 2) {
                j = 0;
            }
        }
    }

    public int getRandomIndexOfLyric(int max) {
        return (int) (Math.random() * (max - 1 + 1) + 1);
    }
}
