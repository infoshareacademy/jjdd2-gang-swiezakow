package com.infoshareacademy.model.databaseinputs;

import java.util.List;

public class StatisticsStore {

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
