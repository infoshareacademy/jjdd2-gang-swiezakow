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

    public RecipientModel() {
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void setSendTimeDate(String sendTimeDate) {
        this.sendTimeDate = sendTimeDate;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipientModel{" +
                "emails=" + emails +
                ",\n sendTimeDate='" + sendTimeDate + '\'' +
                ",\n interval=" + interval +
                ",\n id=" + id +
                '}';
    }
}
