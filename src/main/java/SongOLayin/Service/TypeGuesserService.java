package SongOLayin.Service;

import SongOLayin.Models.FileInformation;
import SongOLayin.Entities.Lyric;

import java.util.*;

public class TypeGuesserService {

    Set<String> dragosteType = new HashSet<>(Arrays.asList("iubire", "iubesc","iubita", "fericit", "frumoasa", "fericire", "fericita", "dragoste", "indragostit", "dorinta"));
    Set<String> sadType = new HashSet<>(Arrays.asList("sufar", "suferinta", "lacrima", "lacrimi", "dor", "plans", "plang", "suparat", "suparare", "cearta", "certam"));
    Set<String> gangstaType = new HashSet<>(Arrays.asList("bani", "smecherie", "smecher", "valoare", "valoarea", "valoros","avere", "banii", "banilor", "laud", "lauda", "invidiosi","invidie","invidiosilor"));


    public Map<String, String> getGuessedType(Map<String, List<Lyric>> l){
        Map<String, String> guessedType = new HashMap<>();
        for(Map.Entry<String, List<Lyric>> listEntry : l.entrySet()){
            guessedType.put(listEntry.getKey(), guessedType(listEntry.getValue()));
        }
        return guessedType;
    }


    public Map<String, List<Lyric>> getNameOfSongsAndLyrics(List<FileInformation> fileInformations){
        Map<String, List<Lyric>> nameAndLyrics = new HashMap<>();
        for(FileInformation f : fileInformations){
            nameAndLyrics.put(f.getSong().getName(), f.getLyrics());
        }
        return nameAndLyrics;
    }

    public String guessedType(List<Lyric> l){
        int dragoste = countLove(transformLyricsToWords(transformLyricsToString(l)));
        int jale = countSad(transformLyricsToWords(transformLyricsToString(l)));
        int smecherie = countGangsta(transformLyricsToWords(transformLyricsToString(l)));
        if(dragoste > jale && dragoste > smecherie){
            return "LOVE";
        }
        if(jale > dragoste && jale > smecherie){
            return "SAD";
        }
        if(smecherie > dragoste && smecherie > jale){
            return "GANGSTA";
        }
        else{
            return "IRRELEVANT";
        }
    }

    private List<String> transformLyricsToString(List<Lyric> lyrics){
        List<String> allLyrics = new ArrayList<>();
        for(Lyric l : lyrics){
            allLyrics.add(l.getLine());
        }
        return allLyrics;
    }

    private List<String> transformLyricsToWords(List<String> list){
        List<String> words = new ArrayList<>();
        for(String s : list){
            StringTokenizer st = new StringTokenizer(s," ");
            while(st.hasMoreTokens()){
                words.add(st.nextToken());
            }
        }
        return words;
    }

    private int countLove(List<String> words){
        int love = 0;
        for(String str : words){
            if(dragosteType.contains(str)){
                love++;
            }
        }
        return love;
    }

    private int countSad(List<String> words){
        int jale = 0;
        for(String str : words){
            if(sadType.contains(str)){
                jale++;
            }
        }
        return jale;
    }

    private int countGangsta(List<String> words){
        int gangsta = 0;
        for(String str : words){
            if(gangstaType.contains(str)){
                gangsta++;
            }
        }
        return gangsta;
    }
}
