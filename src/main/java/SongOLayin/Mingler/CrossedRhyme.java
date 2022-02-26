package SongOLayin.Mingler;

import SongOLayin.Service.RhymeService;

import java.util.ArrayList;
import java.util.List;

//single-method class that provides the specified number of crossed-rhymed lyrics
public class CrossedRhyme {

    RhymeService rhymeService = new RhymeService();

    public void getCrossedLyrics(int noOfLyrics) {

        //firstly obtains two lists with two different rhymes
        List<String> listNo1 = new ArrayList<>(rhymeService.getLyricsWithRhyme());
        List<String> listNo2 = new ArrayList<>(rhymeService.getLyricsWithRhyme());

        //secondly displays random lyrics from them one after the other
        int k = 0;
        while (k < noOfLyrics) {
            int sizeList1 = listNo1.size();
            int sizeList2 = listNo2.size();
            System.out.println(listNo1.get(PairedRhyme.getInstance().getRandomIndexOfLyric(sizeList1) - 1));
            System.out.println(listNo2.get(PairedRhyme.getInstance().getRandomIndexOfLyric(sizeList2) - 1));
            k += 2;
        }
    }
}
