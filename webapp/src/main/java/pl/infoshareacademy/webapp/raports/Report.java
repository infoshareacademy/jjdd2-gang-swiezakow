package pl.infoshareacademy.webapp.raports;

import java.util.List;

public class Report {
    private List<DataSets> datasets;
    private List<String> labels;

    public Report() {
    }

    public Report(List<DataSets> datasets, List<String> labels) {
        this.datasets = datasets;
        this.labels = labels;
    }

    public List<DataSets> getDatasets() {
        return datasets;
    }

    public List<String> getLabels() {
        return labels;
    }
}
