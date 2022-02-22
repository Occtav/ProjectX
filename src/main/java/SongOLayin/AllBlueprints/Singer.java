package SongOLayin.AllBlueprints;


// Simple as can be seen

public class Singer {
    private final int id;
    private final String name;

    public Singer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}