package com.infoshareacademy.service.timerEngineService;

import com.google.common.annotations.VisibleForTesting;
import com.infoshareacademy.model.RecipientModel;
import com.infoshareacademy.model.TasksStore;
import com.infoshareacademy.model.databaseinputs.DataStore;
import com.infoshareacademy.model.databaseinputs.StatisticsStore;
import com.infoshareacademy.service.chartsGenerator.CompareEntriesChart;
import com.infoshareacademy.service.chartsGenerator.RushHoursChart;
import com.infoshareacademy.service.chartsGenerator.VisitsNumberChart;
import com.infoshareacademy.service.emailservice.EmailSender;
import com.infoshareacademy.service.pdfservice.PdfGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class TaskExecutor {

    Logger LOG = LogManager.getLogger(TaskExecutor.class);

    @Inject
    TasksStore tasksStore;

    @Inject
    DataStore dataStore;

    @Schedule(minute = "*/1", hour = "*", persistent = false)
    public void periodic() {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dataStore.getStatisticsStore().toString());
        System.out.println(tasksStore.getBase().toString());
    }

    public void prepareAttachment(StatisticsStore statisticsStore) {

        CompareEntriesChart compareEntriesChart = new CompareEntriesChart();
        RushHoursChart rushHoursChart = new RushHoursChart();
        VisitsNumberChart visitsNumberChart = new VisitsNumberChart();

        try {
            compareEntriesChart.generateCompareEntriesChart(statisticsStore.getLastMonthSumUserActivityInIndividualFeature());
            rushHoursChart.generateRushHoursCharts(statisticsStore.getLastMonthUserActivityIntervalStat());
            visitsNumberChart.getVisitsNumberChart(statisticsStore.getLastMonthUserActivityInIndividualFeature());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PdfGenerator pdfGenerator = new PdfGenerator(
                statisticsStore);
        pdfGenerator.generatePDF();
    }

    public void takeNewTask(String localDateTime) {
        if (tasksStore.getBase().size()>=1) {
            List<RecipientModel> recipientList = new ArrayList<>(tasksStore.getBase().values());
            recipientList = recipientList.stream()
                    .filter(recipientModel -> recipientModel.getSendTimeDate().compareTo(localDateTime)<=0)
                    .collect(Collectors.toList());
            recipientList.forEach(s -> s.getEmails().forEach(e -> new EmailSender().sendEmail(e)));


            Set<Map.Entry<Integer, RecipientModel>> entries = tasksStore.getBase().entrySet();
            for (Map.Entry<Integer, RecipientModel> mapEntry :
                    entries) {
                for (int i = 0; i < recipientList.size(); i++) {
                    if (mapEntry.getValue().getSendTimeDate().equals(recipientList.get(i))) {
                        tasksStore.getBase().put(mapEntry.getKey(),
                                new RecipientModel(
                                        tasksStore.getBase().get(mapEntry.getKey()).getEmails(),
                                        //TODO
                                        setNewTaskTime(
                                                tasksStore.getBase().get(mapEntry.getKey()).getSendTimeDate(),
                                                tasksStore.getBase().get(mapEntry.getKey()).getInterval()
                                        ),
                                        tasksStore.getBase().get(mapEntry.getKey()).getInterval(),
                                        tasksStore.getBase().get(mapEntry.getKey()).getId()));
                    }
                }
            }
        }
    }

    public String setNewTaskTime(String inputDate, Integer interval) {
        LocalDateTime oldTime = LocalDateTime.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return oldTime.plusMinutes(interval).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
