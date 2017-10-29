package com.infoshareacademy.model;

import java.util.List;

public class StatisticsStore {

    private List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature;

    private SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature;

    private List<RushHourModel> lastMonthUserActivityIntervalStat;

    public List<DetailedStatisticsModel> getLastMonthUserActivityInIndividualFeature() {
        return lastMonthUserActivityInIndividualFeature;
    }

    public SumDetailedStaticsModel getLastMonthSumUserActivityInIndividualFeature() {
        return lastMonthSumUserActivityInIndividualFeature;
    }

    public List<RushHourModel> getLastMonthUserActivityIntervalStat() {
        return lastMonthUserActivityIntervalStat;
    }

    public StatisticsStore() {
    }

    public StatisticsStore(List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature,
                           SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature,
                           List<RushHourModel> lastMonthUserActivityIntervalStat) {
        this.lastMonthUserActivityInIndividualFeature = lastMonthUserActivityInIndividualFeature;
        this.lastMonthSumUserActivityInIndividualFeature = lastMonthSumUserActivityInIndividualFeature;
        this.lastMonthUserActivityIntervalStat = lastMonthUserActivityIntervalStat;
    }
}
