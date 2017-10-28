package com.infoshareacademy.model;

import java.time.LocalTime;
import java.util.List;

public class RecipientConfiguration {

    private List<String> emails;

    private String sendTime;

    private Integer interval;

    private StatisticsStore statisticsStore;

    public List<String> getEmails() {
        return emails;
    }

    public String getSendTime() {
        return sendTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public StatisticsStore getStatisticsStore() {
        return statisticsStore;
    }
}
