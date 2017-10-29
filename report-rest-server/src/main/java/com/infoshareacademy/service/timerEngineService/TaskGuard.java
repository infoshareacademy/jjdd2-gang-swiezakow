package com.infoshareacademy.service.timerEngineService;

import com.infoshareacademy.model.RecipientModel;
import com.infoshareacademy.model.StatisticsStore;
import com.infoshareacademy.model.TasksStore;
import com.infoshareacademy.service.chartsGenerator.CompareEntriesChart;
import com.infoshareacademy.service.chartsGenerator.RushHoursChart;
import com.infoshareacademy.service.chartsGenerator.VisitsNumberChart;
import com.infoshareacademy.service.emailservice.EmailSender;
import com.infoshareacademy.service.pdfservice.PdfGenerator;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TaskGuard {

    public static void timer() throws ParseException {

        //the Date and time at which you want to execute
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = dateFormatter.parse(localDateTime);

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it repeatedly
        int period = 60000 * 5;//60secs
        timer.schedule(new MyTimeTask(), date, period );
    }


    //The task which you want to execute
    private static class MyTimeTask extends TimerTask {

        @Inject
        TasksStore tasksStore;

        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        public void run() {
            prepareAttachment();
            takeNewTask();

        }

        private void prepareAttachment() {
            StatisticsStore statisticsStore = new StatisticsStore();

            CompareEntriesChart compareEntriesChart = new CompareEntriesChart(
                    statisticsStore.getLastMonthSumUserActivityInIndividualFeature());
            RushHoursChart rushHoursChart = new RushHoursChart(
                    statisticsStore.getLastMonthUserActivityIntervalStat());
            VisitsNumberChart visitsNumberChart = new VisitsNumberChart(
                    statisticsStore.getLastMonthUserActivityInIndividualFeature());

            try {
                compareEntriesChart.generateCompareEntriesChart();
                rushHoursChart.generateRushHoursCharts();
                visitsNumberChart.getVisitsNumberChart();
            } catch (Exception e) {
                e.printStackTrace();
            }

            PdfGenerator pdfGenerator = new PdfGenerator(
                    statisticsStore);
        }

        private void takeNewTask() {
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

        private String setNewTaskTime(String inputDate, Integer interval) {
            LocalDateTime oldTime = LocalDateTime.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return oldTime.plusMinutes(interval).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

    }

}