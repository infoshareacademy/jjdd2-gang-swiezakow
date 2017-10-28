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
}
