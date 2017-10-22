package pl.infoshareacademy.webapp.raports;

import java.util.List;

public class DataSets {
    private List<String> backgroundColor;
    private List<Integer> data;

    public DataSets() {
    }

    public DataSets(List<String> backgroundColor, List<Integer> data) {
        this.backgroundColor = backgroundColor;
        this.data = data;
    }

    public List<String> getBackgroundColor() {
        return backgroundColor;
    }

    public List<Integer> getData() {
        return data;
    }
}
