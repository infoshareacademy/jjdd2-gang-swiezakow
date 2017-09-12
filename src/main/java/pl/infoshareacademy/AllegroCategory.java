package pl.infoshareacademy;

public class AllegroCategory {
    private int catID;
    private String name;
    private int parent;
    private int catPosition;

    public AllegroCategory(int catID, String name, int parent, int catPosition) {
        this.catID = catID;
        this.name = name;
        this.parent = parent;
        this.catPosition = catPosition;
    }

    public int getCatID() {
        return catID;
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

    @Override
    public String toString() {
        return name;
    }



//    @Override
//    public String toString() {
//        return "AllegroCategory{" +
//                "catID=" + catID +
//                ", name='" + name + '\'' +
//                ", parent=" + parent +
//                ", catPosition=" + catPosition +
//                '}' + "\n";
//    }
}
