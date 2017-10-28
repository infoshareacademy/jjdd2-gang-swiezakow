package pl.infoshareacademy;

public class AllegroCategory {
    private int catID;
    private String name;
    private int parent;
    private int catPosition;
    private int catIsProductCatalogueEnabled;
    private boolean catIsLeaf;

    public AllegroCategory(int catID, String name, int parent, int catPosition, int catIsProductCatalogueEnabled, boolean catIsLeaf) {
        this.catID = catID;
        this.name = name;
        this.parent = parent;
        this.catPosition = catPosition;
        this.catIsProductCatalogueEnabled = catIsProductCatalogueEnabled;
        this.catIsLeaf = catIsLeaf;
        
    }

    public AllegroCategory(int id, String sname, int parentId, int position, int catIsProductCatalogueEnabled) { }

    public AllegroCategory(int id, String sname, int parentId, int position) { }

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

    public int getCatIsProductCatalogueEnabled() {
        return catIsProductCatalogueEnabled;
    }

    public boolean isCatIsLeaf() {
        return catIsLeaf;
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
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = catID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + parent;
        result = 31 * result + catPosition;
        return result;
    }
}
