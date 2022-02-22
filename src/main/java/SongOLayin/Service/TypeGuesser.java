package SongOLayin.Service;

import SongOLayin.AllBlueprints.FileInformation;
import SongOLayin.AllBlueprints.Lyric;

import java.util.*;
import java.util.stream.Collectors;

public class TypeGuesser {

    Set<String> dragosteType = new HashSet<>(Arrays.asList("iubire", "iubesc","iubita", "fericit", "frumoasa", "fericire", "fericita", "dragoste", "indragostit", "dorinta"));
    Set<String> jaleType = new HashSet<>(Arrays.asList("sufar", "suferinta", "lacrima", "lacrimi", "dor", "plans", "plang", "suparat", "suparare", "cearta", "certam"));
    Set<String> jmekerieType = new HashSet<>(Arrays.asList("bani", "smecherie", "smecher", "valoare", "valoarea", "valoros","avere", "banii", "banilor", "laud", "lauda", "invidiosi","invidie","invidiosilor"));


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
        int dragoste = countDragoste(transformLyricsToWords(transformLyricsToString(l)));
        int jale = countJale(transformLyricsToWords(transformLyricsToString(l)));
        int smecherie = countSmecherie(transformLyricsToWords(transformLyricsToString(l)));
        if(dragoste > jale && dragoste > smecherie){
            return "DRAGOSTE";
        }
        if(jale > dragoste && jale > smecherie){
            return "JALE";
        }
        if(smecherie > dragoste && smecherie > jale){
            return "JMEKERIE";
        }
        else{
            return "neconcludent";
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

    private int countDragoste(List<String> words){
        int love = 0;
        for(String str : words){
            if(dragosteType.contains(str)){
                love++;
            }
        }
        return love;
    }

    private int countJale(List<String> words){
        int jale = 0;
        for(String str : words){
            if(jaleType.contains(str)){
                jale++;
            }
        }
        return jale;
    }

    private int countSmecherie(List<String> words){
        int smecherie = 0;
        for(String str : words){
            if(jmekerieType.contains(str)){
                smecherie++;
            }
        }
        return smecherie;

    }
}
