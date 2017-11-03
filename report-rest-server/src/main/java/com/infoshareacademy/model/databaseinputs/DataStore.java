package com.infoshareacademy.model.databaseinputs;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class DataStore {

    private StatisticsStore statisticsStore;

    public StatisticsStore getStatisticsStore() {
        return statisticsStore;
    }

    public void setStatisticsStore(StatisticsStore statisticsStore) {
        this.statisticsStore = statisticsStore;
    }

    public DataStore() {

        List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature = new ArrayList<>();
        DetailedStatisticsModel model = new DetailedStatisticsModel("2017-10-30");
        model.setFeature1Quantity(25);
        model.setFeature2Quantity(5);
        model.setFeature3Quantity(1);
        model.setFeature4Quantity(10);
        model.setVisits(52);
        lastMonthUserActivityInIndividualFeature.add(model);

        SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature = new SumDetailedStaticsModel(
                6,3,8,3,6
        );

        List<RushHourModel> lastMonthUserActivityIntervalStat = new ArrayList<>();
        RushHourModel hourModel = new RushHourModel(0, 5l);
        lastMonthUserActivityIntervalStat.add(hourModel);

        statisticsStore = new StatisticsStore(lastMonthUserActivityInIndividualFeature,
                lastMonthSumUserActivityInIndividualFeature,
                lastMonthUserActivityIntervalStat,
                "2017-10-30 19:50");

    }


}
