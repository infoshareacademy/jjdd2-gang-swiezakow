package pl.infoshareacademy.reportService.ModelsStore;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private StatisticsStore statisticsStore;

    public StatisticsStore getStatisticsStore() {
        return statisticsStore;
    }

    public void setStatisticsStore(StatisticsStore statisticsStore) {
        this.statisticsStore = statisticsStore;
    }

    public DataStore(StatisticsStore statisticsStore) {
        this.statisticsStore = statisticsStore;
    }

}
