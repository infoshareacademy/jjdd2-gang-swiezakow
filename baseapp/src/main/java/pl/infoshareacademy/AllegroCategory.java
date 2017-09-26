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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllegroCategory that = (AllegroCategory) o;

        if (catID != that.catID) return false;
        if (parent != that.parent) return false;
        if (catPosition != that.catPosition) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = catID;
        result = 31 * result + name.hashCode();
        result = 31 * result + parent;
        result = 31 * result + catPosition;
        return result;
    }
}
