package SongOLayin.Entities;


// The rhyme, the hashcode and the length of every lyric are calculated independently.
// We generate the hashcode of every lyric to detect easily the duplicates

public class Lyric {
    private final String line;
    private final String rhyme;
    private final int length;
    private final String songName;
    private final String hashCode;

    public Lyric(String line, String songName, String hashCode) {
        this.line = line.trim();
        this.rhyme = setRhyme();
        this.length = setLength();
        this.songName = songName;
        this.hashCode = hashCode;
    }

    private String setRhyme(){
        return this.line.substring(this.line.length() - 2);
    }

    private int setLength(){
        return this.line.length();
    }

    public String getSongName() {
        return songName;
    }

    public String getLine() {
        return line;
    }

    public String getRhyme() {
        return rhyme;
    }

    public int getLength() {
        return length;
    }

    public String getHashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "line='" + line + '\'' +
                ", rhyme='" + rhyme + '\'' +
                ", length=" + length +
                ", songName='" + songName + '\'' +
                '}';
    }
}
