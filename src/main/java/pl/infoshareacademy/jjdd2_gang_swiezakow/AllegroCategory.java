package pl.infoshareacademy.jjdd2_gang_swiezakow;

public class AllegroCategory {
    private int cadID;
    private String name;
    private int parent;
    private int catPosition;

    public AllegroCategory(int cadID, String name, int parent, int catPosition) {
        this.cadID = cadID;
        this.name = name;
        this.parent = parent;
        this.catPosition = catPosition;
    }

    public int getCadID() {
        return cadID;
    }

    public String getName() {
        return name;
    }

    public int getParent() {
        return parent;
    }

    public int getCatPosition() {
        return catPosition;
    }
}
