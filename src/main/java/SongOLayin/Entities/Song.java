package SongOLayin.Entities;

import SongOLayin.Models.MusicGenre;

public class Song {
    private int id;
    private final String author;
    private final String name;
    private final MusicGenre type;



    public Song(String author, String name, MusicGenre type) {
        this.author = author;
        this.name = name;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public MusicGenre getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", author=" + author +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
