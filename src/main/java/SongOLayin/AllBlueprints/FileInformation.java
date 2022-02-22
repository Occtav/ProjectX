package SongOLayin.AllBlueprints;

import java.util.List;

//FileInformation is an entity that helps reading and stocking lyrics

public class FileInformation {

    private final String authorName;
    private final Song song;
    private final List<Lyric> lyrics;


    public FileInformation(String authorName, Song song, List<Lyric> lyrics) {
        this.authorName = authorName;
        this.song = song;
        this.lyrics = lyrics;
    }


    public String getAuthorName() {
        return authorName;
    }

    public Song getSong() {
        return song;
    }

    public List<Lyric> getLyrics() {
        return lyrics;
    }

    @Override
    public String toString() {
        return "FileInformation{" +
                "author_name='" + authorName + '\'' +
                ", song=" + song +
                '}';
    }
}

