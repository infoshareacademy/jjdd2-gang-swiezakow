package com.infoshareacademy.model;

import com.infoshareacademy.service.chartsGenerator.CompareEntriesChart;
import com.infoshareacademy.service.chartsGenerator.RushHoursCharts;
import com.infoshareacademy.service.chartsGenerator.VisitsNumberChart;
import com.infoshareacademy.service.pdfservice.PdfGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TaskModel {

    private Integer id;

    private RecipientConfiguration recipientConfiguration;


    public TaskModel(Integer id, RecipientConfiguration recipientConfiguration) throws ParseException {
        this.id = id;
        this.recipientConfiguration = recipientConfiguration;
        timer(recipientConfiguration);
    }

    public Integer getId() {
        return id;
    }

    public RecipientConfiguration getRecipientConfiguration() {
        return recipientConfiguration;
    }


    public void timer(RecipientConfiguration recipientConfiguration) throws ParseException {

        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = dateFormatter.parse(recipientConfiguration.getSendTime());

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it repeatedly
        int period = 60000 * recipientConfiguration.getInterval();//60secs
        timer.schedule(new MyTimeTask(), date, period );
    }


    //The task which you want to execute
    private class MyTimeTask extends TimerTask {

        public void run() {
            CompareEntriesChart compareEntriesChart = new CompareEntriesChart(
                    recipientConfiguration.getStatisticsStore().getLastMonthSumUserActivityInIndividualFeature());
            RushHoursCharts rushHoursCharts = new RushHoursCharts(
                    recipientConfiguration.getStatisticsStore().getLastMonthUserActivityIntervalStat());
            VisitsNumberChart visitsNumberChart = new VisitsNumberChart(
                    recipientConfiguration.getStatisticsStore().getLastMonthUserActivityInIndividualFeature());

            PdfGenerator pdfGenerator = new PdfGenerator(
                    recipientConfiguration.getStatisticsStore());


        }
    }

}
