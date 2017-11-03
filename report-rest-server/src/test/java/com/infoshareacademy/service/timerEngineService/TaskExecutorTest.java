package com.infoshareacademy.service.timerEngineService;

import com.infoshareacademy.model.RecipientModel;
import com.infoshareacademy.model.TasksStore;
import com.infoshareacademy.model.databaseinputs.DetailedStatisticsModel;
import com.infoshareacademy.model.databaseinputs.RushHourModel;
import com.infoshareacademy.model.databaseinputs.StatisticsStore;
import com.infoshareacademy.model.databaseinputs.SumDetailedStaticsModel;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class TaskExecutorTest {
    @Test
    public void takeNewTask() throws Exception {
        //given
        TasksStore tasksStore = new TasksStore();
        TaskExecutor sut = new TaskExecutor();
        //when
        sut.takeNewTask(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), tasksStore);
        //then
        assertThat(tasksStore.getBase().get(1).getSendTimeDate())
                .matches(LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void prepareAttachment() throws Exception {
        //given
        List<DetailedStatisticsModel> lastMonthUserActivityInIndividualFeature = new ArrayList<>();
        DetailedStatisticsModel model = new DetailedStatisticsModel("2017-10-30");
        model.setFeature1Quantity(25);
        model.setFeature2Quantity(5);
        model.setFeature3Quantity(1);
        model.setFeature4Quantity(10);
        model.setVisits(52);
        lastMonthUserActivityInIndividualFeature.add(model);

        SumDetailedStaticsModel lastMonthSumUserActivityInIndividualFeature = new SumDetailedStaticsModel(
                6,3,8,3,6
        );

        List<RushHourModel> lastMonthUserActivityIntervalStat = new ArrayList<>();
        RushHourModel hourModel = new RushHourModel(0, 5l);
        lastMonthUserActivityIntervalStat.add(hourModel);

        StatisticsStore given = new StatisticsStore(lastMonthUserActivityInIndividualFeature,
                lastMonthSumUserActivityInIndividualFeature,
                lastMonthUserActivityIntervalStat,
                "2017-10-30 19:50");

        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        /********************************************************************************/
        TaskExecutor sut = new TaskExecutor();
        //when
        sut.prepareAttachment(given);
        //then
        assertThat(listOfFiles).contains(new File("./BarChart.png"));
    }

    @Test
    public void periodic() throws Exception {
        //given

        TaskExecutor sut = new TaskExecutor();
        String time = "2017-11-31 23:59";
        Integer interval = 10;

        //when

        String result = sut.setNewTaskTime(interval);

        //then

        assertThat(result).containsPattern(Pattern.compile(LocalDateTime.now().plusMinutes(10)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
    }

}