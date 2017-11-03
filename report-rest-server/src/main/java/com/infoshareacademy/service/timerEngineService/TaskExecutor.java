package com.infoshareacademy.service.timerEngineService;

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
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Startup
@Singleton
public class TaskExecutor {

    private static final Logger LOG = LogManager.getLogger(TaskExecutor.class);

    @Inject
    TasksStore tasksStore;

    @Inject
    DataStore dataStore;

    @Schedule(minute = "*/1", hour = "*")
    public void periodic() {
        prepareAttachment(dataStore.getStatisticsStore());
        LOG.info("Prepared attachment");
        System.out.println(dataStore.getStatisticsStore().toString());
        System.out.println(tasksStore.getBase().toString());
        if (tasksStore.getBase().size()>=1) {
            takeNewTask(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), tasksStore);
        }


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

    public void takeNewTask(String localDateTime, TasksStore tasksStore) {
        List<RecipientModel> recipientList = new ArrayList<>(tasksStore.getBase().values());
        recipientList = recipientList.stream()
                .filter(recipientModel -> recipientModel.getSendTimeDate().compareTo(localDateTime)<0)
                .collect(Collectors.toList());
        recipientList.forEach(s -> s.getEmails().forEach(e -> new EmailSender().sendEmail(e)));


        TasksStore tasksStore1 = tasksStore;
        updateDataStore(tasksStore1, recipientList);
    }

    private void updateDataStore(TasksStore tasksStore, List<RecipientModel> recipientList) {
        Set<Map.Entry<Integer, RecipientModel>> entries = tasksStore.getBase().entrySet();
        for (Map.Entry<Integer, RecipientModel> mapEntry :
                entries) {
            for (int i = 0; i < recipientList.size(); i++) {
                if (mapEntry.getValue().getSendTimeDate().equals(recipientList.get(i).getSendTimeDate())) {
                    tasksStore.getBase().put(mapEntry.getKey(),
                            new RecipientModel(
                                    tasksStore.getBase().get(mapEntry.getKey()).getEmails(),
                                    setNewTaskTime(
                                            tasksStore.getBase().get(mapEntry.getKey()).getInterval()
                                    ),
                                    tasksStore.getBase().get(mapEntry.getKey()).getInterval(),
                                    tasksStore.getBase().get(mapEntry.getKey()).getId()));
                }
            }
        }
    }

    public String setNewTaskTime(Integer interval) {
        LocalDateTime now = LocalDateTime.now();
        return now.plusMinutes(interval).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
