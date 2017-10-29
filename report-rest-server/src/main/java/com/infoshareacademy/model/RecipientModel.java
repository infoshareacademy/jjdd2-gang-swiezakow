package com.infoshareacademy.model;

import java.util.List;

public class RecipientModel {

    private List<String> emails;

    private String sendTimeDate;

    private Integer interval;

    private Integer id;

    public List<String> getEmails() {
        return emails;
    }

    public String getSendTimeDate() {
        return sendTimeDate;
    }

    public Integer getInterval() {
        return interval;
    }

    public Integer getId() {
        return id;
    }

    public RecipientModel(List<String> emails, String sendTimeDate, Integer interval, Integer id) {
        this.emails = emails;
        this.sendTimeDate = sendTimeDate;
        this.interval = interval;
        this.id = id;
    }
}
