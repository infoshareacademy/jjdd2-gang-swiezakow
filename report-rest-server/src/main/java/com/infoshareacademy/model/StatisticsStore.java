package com.infoshareacademy.model;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StatisticsStore {

    public StatisticsStore() {
        lastMonthSumUserActivityInIndividualFeature = new SumDetailedStaticsModel(
                6,3,8,3,6
        );

        lastMonthUserActivityInIndividualFeature = new ArrayList<>();
        DetailedStatisticsModel model = new DetailedStatisticsModel("2017-10-30");
        model.setFeature1Quantity(25);
        model.setFeature2Quantity(5);
        model.setFeature3Quantity(1);
        model.setFeature4Quantity(10);
        model.setVisits(52);
        lastMonthUserActivityInIndividualFeature.add(model);

        lastMonthUserActivityIntervalStat = new ArrayList<>();
        RushHourModel hourModel = new RushHourModel(0, 5l);
        lastMonthUserActivityIntervalStat.add(hourModel);
    }

    private List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature;

    private SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature;

    private List<RushHourModel> lastMonthUserActivityIntervalStat;

    private String updateDate;

    public List<DetailedStatisticsModel> getLastMonthUserActivityInIndividualFeature() {
        return lastMonthUserActivityInIndividualFeature;
    }

    public SumDetailedStaticsModel getLastMonthSumUserActivityInIndividualFeature() {
        return lastMonthSumUserActivityInIndividualFeature;
    }

    public List<RushHourModel> getLastMonthUserActivityIntervalStat() {
        return lastMonthUserActivityIntervalStat;
    }

    public void updateStatisticsStore(
            List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature,
            SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature,
            List<RushHourModel> lastMonthUserActivityIntervalStat,
            String updateDate) {

            this.lastMonthUserActivityInIndividualFeature = lastMonthUserActivityInIndividualFeature;

            this.lastMonthSumUserActivityInIndividualFeature = lastMonthSumUserActivityInIndividualFeature;

            this.lastMonthUserActivityIntervalStat = lastMonthUserActivityIntervalStat;

            this.updateDate = updateDate;
    }

}
